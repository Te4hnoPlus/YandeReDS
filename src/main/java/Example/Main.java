package Example;


public class Main {

    public static ExampleBot exampleBot;
    public static final String BOT_NAME = "example_bot";

    public static void main(String[] args){
        /*
        Create a connection for your bot by passing as arguments the name
        (may not match the nickname of the bot) and the token to connect.
        A file with basic settings will be created in the working folder of the bot.
        If you did not specify a token in the constructor, it will be taken from the settings file.
         */
        if(args != null && args.length>1){
            exampleBot = new ExampleBot(BOT_NAME, args[0]);
        } else {
            exampleBot = new ExampleBot(BOT_NAME);
        }
    }
}
