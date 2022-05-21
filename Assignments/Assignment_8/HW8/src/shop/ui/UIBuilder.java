package shop.ui;


public interface UIBuilder<U,V,W> {
        
    public void add(U u, V v);

    public W toUITemplate(String string);
}
