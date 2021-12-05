// Generated by view binder compiler. Do not edit!
package com.example.myfirstapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myfirstapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PastorderItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView histOrderid;

  @NonNull
  public final TextView histStats;

  @NonNull
  public final TextView histTotalprice;

  @NonNull
  public final ConstraintLayout pastOrderItem;

  @NonNull
  public final TextView textView25;

  @NonNull
  public final TextView textView29;

  private PastorderItemBinding(@NonNull ConstraintLayout rootView, @NonNull TextView histOrderid,
      @NonNull TextView histStats, @NonNull TextView histTotalprice,
      @NonNull ConstraintLayout pastOrderItem, @NonNull TextView textView25,
      @NonNull TextView textView29) {
    this.rootView = rootView;
    this.histOrderid = histOrderid;
    this.histStats = histStats;
    this.histTotalprice = histTotalprice;
    this.pastOrderItem = pastOrderItem;
    this.textView25 = textView25;
    this.textView29 = textView29;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PastorderItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PastorderItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.pastorder_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PastorderItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hist_orderid;
      TextView histOrderid = ViewBindings.findChildViewById(rootView, id);
      if (histOrderid == null) {
        break missingId;
      }

      id = R.id.hist_stats;
      TextView histStats = ViewBindings.findChildViewById(rootView, id);
      if (histStats == null) {
        break missingId;
      }

      id = R.id.hist_totalprice;
      TextView histTotalprice = ViewBindings.findChildViewById(rootView, id);
      if (histTotalprice == null) {
        break missingId;
      }

      ConstraintLayout pastOrderItem = (ConstraintLayout) rootView;

      id = R.id.textView25;
      TextView textView25 = ViewBindings.findChildViewById(rootView, id);
      if (textView25 == null) {
        break missingId;
      }

      id = R.id.textView29;
      TextView textView29 = ViewBindings.findChildViewById(rootView, id);
      if (textView29 == null) {
        break missingId;
      }

      return new PastorderItemBinding((ConstraintLayout) rootView, histOrderid, histStats,
          histTotalprice, pastOrderItem, textView25, textView29);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
