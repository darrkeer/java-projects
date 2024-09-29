package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup implements PrimitiveMarkup{
    public Strikeout(List<PrimitiveMarkup> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() {
        return "~";
    }

    @Override
    public String getBBCodePrefix() {
        return "[s]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "[/s]";
    }
}
