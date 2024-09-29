package markup;

import java.util.List;

public abstract class AbstractMarkup implements MarkupElement{
    protected List<MarkupElement> content;

    public AbstractMarkup(List<MarkupElement> content){
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(getMarkdownMarkup());
        for(MarkupElement el : content)
            el.toMarkdown(s);
        s.append(getMarkdownMarkup());
    }

    @Override
    public void toBBCode(StringBuilder s) {
        s.append(getBBCodePrefix());
        for(MarkupElement el : content)
            el.toBBCode(s);
        s.append(getBBCodeSuffix());
    }
}
