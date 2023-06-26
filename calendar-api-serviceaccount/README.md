## HISTORY OF COMMANDS

```bash
# Install Gradle with SDKMAN!
sdk install gradle

# Initialize new project
mkdir calendar-api-serviceaccount
cd calendar-api-serviceaccount
gradle init --type basic
# options: 1 and no
mkdir -p src/main/java src/main/resources

# IMPORTANT!
# credentials.json need to be placed in src/main/resources/

# Run sample
gradle run
```