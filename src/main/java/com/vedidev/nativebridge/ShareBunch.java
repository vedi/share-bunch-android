package com.vedidev.nativebridge;

import android.content.Context;
import android.content.Intent;
import org.json.JSONObject;

/**
 * @author vedi
 *         date 05/10/14
 */
@SuppressWarnings("UnusedDeclaration")
public class ShareBunch implements Bunch {
    private Context context;

    public ShareBunch() {
        registerProcessor("shareText", new ProcessorEngine.CallHandler() {
            @Override
            public void handle(JSONObject params, JSONObject retParams) throws Exception {
                String text = params.getString("text");
                shareText(text);
            }
        });
    }

    private void shareText(String text) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        Intent chooser = Intent.createChooser(sharingIntent, "Share using");
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooser);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    private void registerProcessor(String key, ProcessorEngine.CallHandler callHandler) {
        ProcessorEngine.getInstance().registerProcessor("ShareBunch", key, callHandler);
    }
}
