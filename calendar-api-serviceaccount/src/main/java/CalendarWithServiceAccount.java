import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class CalendarWithServiceAccount {

  private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
  private static final String CALENDAR_ID = "your_calendar_id@gmail.com";

  private static HttpCredentialsAdapter getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    InputStream in = CalendarWithServiceAccount.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    GoogleCredentials googleCredentials = ServiceAccountCredentials.fromStream(in).createScoped(SCOPES);
    return new HttpCredentialsAdapter(googleCredentials);
  }

  public static void main(String... args) throws IOException, GeneralSecurityException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME).build();

    // List the next 15 events from calendar.
    DateTime now = new DateTime(System.currentTimeMillis());
    Events events = service.events().list(CALENDAR_ID)
        .setMaxResults(15)
        .setTimeMin(now)
        .setOrderBy("startTime")
        .setSingleEvents(true)
        .execute();
    List<Event> items = events.getItems();
    if (items.isEmpty()) {
      System.out.println("No upcoming events found.");
    } else {
      System.out.println("Upcoming events");
      for (Event event : items) {
        DateTime start = event.getStart().getDateTime();
        if (start == null) start = event.getStart().getDate();
        System.out.printf("%s (%s)\n", event.getSummary(), start);
      }
    }
  }
}