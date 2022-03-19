package plus.BigLabel;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import plus.Utl.AutoReloadable;
import plus.Utl.FastRandom;


public class BigLabel extends AutoReloadable {

    protected final String NAME;
    protected final String[] placeHolders;
    protected String format;
    protected BConfig c;

    public BigLabel(String name, String... placeHolders) {
        this.placeHolders = placeHolders;
        NAME = name;
        c = new BConfig(name);
        format = c.getOrWriteMainFormat();
        String rc = c.getRawContent();

        for(String s:placeHolders){
            if(!rc.contains("%"+s+"%")) c.addComment(String.format("used placeHolder [%%%s%%]",s));
        }
    }


    public String getName(){
        return NAME;
    }


    public void reload(){
        c = new BConfig(NAME);
        format = c.getOrWriteMainFormat();
    }


    public MessageEmbed toEmbed(PlaceHolder... ps){

        String url = c.getOrWrite("down_image_url", "none");
        MessageEmbed.ImageInfo image = !url.equalsIgnoreCase("none")? new MessageEmbed.ImageInfo(
                url, url, 1024, 1024
        ):null;
        String smallLogo = c.getOrWrite("small_logo_url", "none");

        MessageEmbed.Thumbnail mi = !url.equalsIgnoreCase("none")? new MessageEmbed.Thumbnail(
                smallLogo,
                smallLogo,
                1024,
                1024
        ):null;

        String title = c.getOrWrite("title", "none");
        if(title.equalsIgnoreCase("none"))title = null;

        int color;
        try {
            color = Integer.parseInt(
                    c.getOrWrite("color_code", String.valueOf(FastRandom.r.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE))
                    )
            );
        }catch (NumberFormatException e){
            color = FastRandom.r.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        String content = format;
        for(PlaceHolder p:ps){
            for(String check:placeHolders){
                if(check.equals(p.VAR)){
                    content = content.replace("%"+p.VAR+"%", p.VALUE);
                    break;
                }
            }
        }

        MessageEmbed es = new MessageEmbed(
                null,
                title,
                content,
                EmbedType.RICH,
                null,
                color,
                mi,
                null,
                null,
                null,
                null,
                image,
                null
        );
        return new EmbedBuilder(es).build();
    }
}