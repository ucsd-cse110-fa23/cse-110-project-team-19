# Team 19

https://github.com/ucsd-cse110-fa23/cse-110-project-team-19

## Getting Started
Make sure you have Java, JavaFX, Gradle, and VSCode installed on your local machine.

Step by step instructions for running the app:
1. Within VSCode, go to the run and debug section and create a launch.json file.
2. In the launch.json file, make sure to include the dependencies for JavaFX here for each configuration in a line that looks like "vmargs": "--module-path 'your-path-to/javafx-sdk-21/lib' --add-modules javafx.controls,javafx.fxml"
3. In the run and debug section, first start the server by pressing the green arrow on the MyServer tab.
4. Then, in the same area without stopping the server, press the green arrow on the App tab to start running the app.

Good luck with your recipes!

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
