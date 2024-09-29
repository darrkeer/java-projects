package markup;

import java.util.List;

public class Strong extends AbstractMarkup implements PrimitiveMarkup{
    public Strong(List<PrimitiveMarkup> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() {
        return "__";
    }

    @Override
    public String getBBCodePrefix() {
        return "[b]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "[/b]";
    }
}
