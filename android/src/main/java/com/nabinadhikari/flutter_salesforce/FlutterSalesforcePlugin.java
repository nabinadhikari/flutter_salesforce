package com.nabinadhikari.flutter_salesforce;

import androidx.annotation.NonNull;
import android.content.Context;
import android.app.Activity;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;

import com.salesforce.android.chat.core.ChatConfiguration;
import com.salesforce.android.chat.ui.ChatUI;
import com.salesforce.android.chat.ui.ChatUIClient;
import com.salesforce.android.chat.ui.ChatUIConfiguration;
import com.salesforce.android.chat.ui.model.QueueStyle;
import com.salesforce.android.service.common.utilities.control.Async;
import com.salesforce.android.chat.core.model.ChatEntity;
import com.salesforce.android.chat.core.model.ChatEntityField;
import com.salesforce.android.chat.core.model.ChatUserData;
import com.salesforce.android.chat.core.model.PreChatEntityField;
import com.salesforce.android.chat.ui.model.PreChatTextInputField;

public class FlutterSalesforcePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  private MethodChannel channel;
  private Context mContext;
  private Activity activity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    this.mContext = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_salesforce");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    activity = null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivity() {
    activity = null;
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("initiateChat")) {
      // Todo
      // result.success("Android " + android.os.Build.VERSION.RELEASE);
      assert(call.argument("credentials") instanceof Map);
      Map credentials = (Map) call.argument("credentials");
      String ORG_ID = (String) credentials.get("orgId");
      String DEPLOYMENT_ID = (String) credentials.get("deploymentId");
      String BUTTON_ID = (String) credentials.get("buttonId");
      String LIVE_AGENT_POD = (String) credentials.get("liveAgentPod");
      assert(call.argument("request") instanceof Map);
      Map request = (Map) call.argument("request");

      ChatConfiguration chatConfiguration = 
        new ChatConfiguration.Builder(ORG_ID, BUTTON_ID, 
                                DEPLOYMENT_ID, LIVE_AGENT_POD)
                                .build();
      ChatUI.configure(ChatUIConfiguration.create(chatConfiguration))
        .createClient(mContext)
        .onResult(new Async.ResultHandler<ChatUIClient>() {
            @Override public void handleResult (Async<?> operation, 
              ChatUIClient chatUIClient) {
                  chatUIClient.startChatSession(activity);
            }
      });                         
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
