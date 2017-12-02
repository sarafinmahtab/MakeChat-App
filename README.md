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

You need to import both MakeChatClient and MakeChatServer project to your Eclipse IDE. Both of the server and client project has `Main.java` as launcher Java Class. Do the below things.
* In cmd, type `ipconfig` and get your server ip.
* In makechat_db.sql, go to 54 line and change the `server_url` and `port_number` as your ip and desired port number. (If you face problem, you can do it in phpmyadmin.)
* Open up Xampp Server and go to phpmyadmin. Create a database named as `makechat_db`.

Now you're ready to run the system.
* First, run `Main.java` class from MakeChatServer project. Then provide that port number you've given in database. Make sure that this port number is free. Now click `Open Server` Button.
* Now, run `Main.java` class from MakeChatClient project. Now give an username, give the server ip address and the port number you've given. Press `Connect` Button.

You're on now. run `Main.java` class from MakeChatClient project multiple times to create multiple clients.

### Obstacles

* No online domain!! That's why, the app can only save previous messages in localhost.
* Multiple Clients should be in a same network.
