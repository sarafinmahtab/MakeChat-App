# Make Chat Application

Make Chat is a desktop chat application where multiple client user connect them through server and can chat continously. This project is an academic project currently at under construction.

## Getting Started

The overall project is in two part. One is MakeChat Server and MakeChat Client. You need to run both the project in Eclipse IDE.

### Prerequisites

You need to install these softwares to run the project.

* [Eclipse Standard Edition](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/oxygen1a) - The IDE used

To get chat history, you need to install this software.
* [XAMPP](https://www.apachefriends.org/index.html) - The Apache Server and MySQL Database

### How to run

You need to import both MakeChatClient and MakeChatServer repo to your Eclipse IDE. Both of the project has Main.java as launcher Java Class. Do the below things.
* In cmd, type 'ipconfig' and get your server ip.
* In makechat_db.sql, go to 54 line and change the `server_url` and `port_number` as your ip and desired port number.
