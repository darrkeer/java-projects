package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup implements PrimitiveMarkup{
    public Emphasis(List<PrimitiveMarkup> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() {
        return "*";
    }

    @Override
    public String getBBCodePrefix() {
        return "[i]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "[/i]";
    }
}
