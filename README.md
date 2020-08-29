## Building The Application

You can build the application on the command line with

    ./gradlew build

Omit the `./` on Windows.  Note that this will be fairly slow the first
time it runs, as it will need to download Gradle itself and the project's
dependencies.  Subsequent builds should be a lot faster.

## Running The Application

### With Gradle  

You can run the application without command line arguments using:

    ./gradlew run


Use the `--args` option if you wish to supply command line arguments to
the application.  For example, to specify a data filename as the sole
command line argument, you do this:

    ./gradlew run --args data/bradford.txt

To specify a data filename and a year as command line arguments, you
need to enclose both in quotes, like this:

    ./gradlew run --args "data/bradford.txt 2018"

### Outside of Gradle

You can generate a self-contained executable JAR file for the application
with

    ./gradlew jar

