package Example;

import plus.Utl.LangProvider;
import plus.YandeRe.YaDsBot;


public class ExampleBot extends YaDsBot {

    protected ExampleChatListener exampleChatListener;
    protected ExampleCommands exampleCommands;
    protected ExampleHidedCommands exampleHidedCommands;
    protected ExampleMenus exampleMenus;

    public ExampleBot(String name, String token) {
        super(name, token);
    }
    public ExampleBot(String name) {
        super(name);
    }

    @Override
    public void onInit() {
        /*
        Use for define global Language provider.
        All components that support the use of translations will take values from this file.
        If the specified file does not exist, a new one will be created.
        During operation, missing values may be written to the file.
         */
        LangProvider.setGlobalProvider(new LangProvider("translations.yml"));

        exampleChatListener = new ExampleChatListener(jda);

        exampleCommands = new ExampleCommands(jda);

        exampleHidedCommands = new ExampleHidedCommands(jda, exampleChatListener, exampleCommands);

        exampleMenus = new ExampleMenus(jda, exampleHidedCommands);
    }
}
