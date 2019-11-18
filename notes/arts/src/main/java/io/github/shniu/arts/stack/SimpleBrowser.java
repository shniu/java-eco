package io.github.shniu.arts.stack;

public class SimpleBrowser {

    private String currentPage;
    private Stack back = new LinkedStack();
    private Stack forward = new LinkedStack();

    public void open(String url) {
        if (currentPage != null) {
            back.push(currentPage);
            forward.clear();
        }
        currentPage = url;
    }

    public String goBack() {
        return null;
    }

    public String goForward() {
        return null;
    }

    public String getCurrentPage() {
        return null;
    }

    public boolean canGoBack() {
        return false;
    }

    public boolean canGoForward() {
        return false;
    }
}
