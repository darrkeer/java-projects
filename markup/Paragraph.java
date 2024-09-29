package markup;

import java.util.List;

public class Paragraph extends AbstractMarkup implements IterableMarkup{
    public Paragraph(List<PrimitiveMarkup> content) {
        super(List.copyOf(content));
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
