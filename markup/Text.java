package markup;

public class Text implements PrimitiveMarkup{
    private String content;

    public Text(String content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(content);
    }

    @Override
    public void toBBCode(StringBuilder s) {
        s.append(content);
    }

    @Override
    public String getMarkdownMarkup() {
        return "";
    }

    @Override
    public String getBBCodePrefix() {
        return "";
    }

    @Override
    public String getBBCodeSuffix() {
        return "";
    }
}
