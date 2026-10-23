package vn.edu.vhu.ltdd.a8fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsDetailFragment extends Fragment {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_CONTENT = "key_content";
    private static final String REQUEST_KEY = "news_request";

    public NewsDetailFragment() {
    }

    public static NewsDetailFragment newInstance(
            String title, String content) {

        NewsDetailFragment fragment = new NewsDetailFragment();

        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_CONTENT, content);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = requireArguments();

        String title = args.getString(KEY_TITLE, "");
        String content = args.getString(KEY_CONTENT, "");

        TextView tvTitle = view.findViewById(R.id.tvTieuDe);
        TextView tvContent = view.findViewById(R.id.tvNoiDung);
        Button btnRead = view.findViewById(R.id.btnDaDoc);

        tvTitle.setText(title);
        tvContent.setText(content);

        btnRead.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString(KEY_TITLE, title);

            getParentFragmentManager().setFragmentResult(
                    REQUEST_KEY, result);

            getParentFragmentManager().popBackStack();
        });
    }
}
