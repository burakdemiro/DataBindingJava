package com.bydmr.databindingjava.databindings;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bydmr.databindingjava.R;

import androidx.databinding.BindingAdapter;

public class GlideBindingAdapter {

    @BindingAdapter("resimGoster")
    public static void resimGoster(ImageView imageView, int drawable) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(drawable)
                .into(imageView);
    }

    // url üzerinden resim göstermek için method overloading yaptım
    @BindingAdapter("resimGoster")
    public static void resimGoster(ImageView imageView, String resimUrl) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(resimUrl)
                .into(imageView);
    }

}
