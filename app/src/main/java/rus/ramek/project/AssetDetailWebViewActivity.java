package rus.ramek.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AssetDetailWebViewActivity extends AppCompatActivity {
    private static final String INTENT_PARAM_ASSET_DETAIL_URL = "INTENT_PARAM_ASSET_DETAIL_URL";
    private WebView webView;
    private ProgressBar loadingProgressBar;
    private String assetDetailUrl;

    public static Intent callingIntent(Context context, String assetDetailUrl){
        Intent intent = new Intent(context,AssetDetailWebViewActivity.class);
        intent.putExtra(INTENT_PARAM_ASSET_DETAIL_URL,assetDetailUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail_web_view);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.assetDetail_webView);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loading_progressBar);
        assetDetailUrl = getIntent().getStringExtra(INTENT_PARAM_ASSET_DETAIL_URL);
        initInstances();
    }

    private void initInstances() {
        webView.setWebViewClient(new AssetWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);


        loadingProgressBar.setVisibility(View.VISIBLE);
        //webView.setVisibility(View.GONE);
        webView.loadUrl(assetDetailUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    final class AssetWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(assetDetailUrl);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadingProgressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }
}
