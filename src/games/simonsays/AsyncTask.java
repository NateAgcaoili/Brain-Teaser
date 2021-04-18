package games.simonsays;

import javafx.application.Platform;

/**
 * @author Victor Oliveira
 */
public abstract class AsyncTask<T1, T2, T3> {

    private boolean daemon = true;

    private T1[] params;

    public abstract void onPreExecute();

    public abstract T3 doInBackground(T1... params) throws InterruptedException;

    public abstract void onPostExecute(T3 params) throws Exception;

    public abstract void progressCallback(T2... params);

    public void publishProgress(final T2... progressParams) {
        Platform.runLater(() -> progressCallback(progressParams));
    }

    public final Thread backGroundThread = new Thread(new Runnable() {
        @Override
        public void run() {

            final T3 param;
            try {
                param = doInBackground(params);
                Platform.runLater(() -> {
                    try {
                        onPostExecute(param);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    });

    public void execute(final T1... params) {
        this.params = params;
        Platform.runLater(() -> {

            onPreExecute();

            backGroundThread.setDaemon(daemon);
            backGroundThread.start();
        });
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public final boolean isInterrupted() {
        return this.backGroundThread.isInterrupted();
    }

    public final boolean isAlive() {
        return this.backGroundThread.isAlive();
    }
}