package br.dev.wesraiuga.snake;

import java.awt.Font;

public class Message {
    private final String text;
    private final Font font;

    public Message(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String text;
        private Font font;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder font(String fontFamily, int fontSize, int style) {
            this.font = new Font(fontFamily, style, fontSize);
            return this;
        }

        public Message build() {
            return new Message(text, font);
        }
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

}
