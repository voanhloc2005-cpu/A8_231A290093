package vn.edu.vhu.ltdd.a8fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsListFragment extends Fragment {

    private static final String KEY_TITLE = "key_title";
    private static final String REQUEST_KEY = "news_request";

    private final String[] titles = {
            "VHU khai giảng năm học mới",
            "Android Studio ra bản cập nhật",
            "Flutter 3.47 có gì mới?"
    };

    private final String[] contents = {
            "Trường Đại học Văn Hiến tổ chức lễ khai giảng năm học mới với nhiều hoạt động dành cho sinh viên.",
            "Bản cập nhật Android Studio cải thiện công cụ lập trình, giao diện và hiệu suất làm việc.",
            "Flutter tiếp tục cải tiến hiệu năng và cung cấp nhiều công cụ hỗ trợ phát triển ứng dụng."
    };

    private TextView tvDaDoc;

    public NewsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout boxTin = view.findViewById(R.id.boxTin);
        tvDaDoc = view.findViewById(R.id.tvDaDoc);

        getParentFragmentManager().setFragmentResultListener(
                REQUEST_KEY,
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    String title = result.getString(KEY_TITLE, "");
                    tvDaDoc.setText("Đã đọc: " + title);
                }
        );

        boxTin.removeAllViews();

        for (int i = 0; i < titles.length; i++) {
            final int position = i;

            Button button = new Button(requireContext());
            button.setText(titles[i]);
            button.setAllCaps(false);

            button.setOnClickListener(v -> {
                NewsDetailFragment detail =
                        NewsDetailFragment.newInstance(
                                titles[position], contents[position]);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, detail)
                        .addToBackStack("chi_tiet")
                        .commit();
            });

            boxTin.addView(button);
        }
    }
}
