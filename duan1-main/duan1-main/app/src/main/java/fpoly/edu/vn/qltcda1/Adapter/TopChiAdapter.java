package fpoly.edu.vn.qltcda1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.fragment.BieuDoFragment;
import fpoly.edu.vn.qltcda1.model.KhoanChi;
import fpoly.edu.vn.qltcda1.model.TopKhoanChi;

public class TopChiAdapter extends ArrayAdapter<TopKhoanChi> {
    private Context context;
    BieuDoFragment bieuDoFragment;
    ArrayList<TopKhoanChi>list;
    TextView tvLoaiChi,tvSoTien;
    ImageView imgtopchi;

    public TopChiAdapter(@NonNull Context context, BieuDoFragment bieuDoFragment, ArrayList<TopKhoanChi> list) {
        super(context, 0,list);
        this.context = context;
        this.bieuDoFragment = bieuDoFragment;
        this.list = list;
        this.tvLoaiChi = tvLoaiChi;
        this.tvSoTien = tvSoTien;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item_chi,null);

        }
        final TopKhoanChi item= list.get(position);
        if (item!=null){
            tvLoaiChi=v.findViewById(R.id.tvTenLoaiC);
            tvLoaiChi.setText(""+item.getTenKhoanChi());
           tvSoTien= v.findViewById(R.id.tvSTC);
            tvSoTien.setText(item.getSoTienChi()+" VND ");
        }

        //set icon loaichi
        //đang lỗi phải làm lại DAO,model mới set được
//        TopKhoanChi it = list.get(position);
//        imgtopchi = v.findViewById(R.id.imgtopchi);
//        if (String.valueOf(it.getMaLoaiChi()).equals("1")){
//            imgtopchi.setImageResource(R.drawable.thuenha);
//        }
//        if (String.valueOf(it.getMaLoaiChi()).equals("2")){
//            imgtopchi.setImageResource(R.drawable.anuong);
//        }
//        if (String.valueOf(it.getMaLoaiChi()).equals("3")){
//            imgtopchi.setImageResource(R.drawable.dilai);
//        }
//        if (!String.valueOf(it.getMaLoaiChi()).equals("1") && !String.valueOf(it.getMaLoaiChi()).equals("2") && !String.valueOf(it.getMaLoaiChi()).equals("3")){
//            imgtopchi.setImageResource(R.drawable.money_c);
//        }

        return v;
    }
}
