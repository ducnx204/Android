package fpoly.edu.grocerymanager.database;

public class Data_SQLite {

    public static final String INSERT_NGUOI_DUNG =
            "INSERT INTO NguoiDung (maND, hoTen, matKhau, soDienThoai) values \n" +
                    "('NgocHai','Lương Ngọc Hải', '123', '0911602532'),\n" +
                    "('admin','Lê Gôn', 'admin', '0911602532'),\n" +
                    "('HongThi','Nguyễn Hồng Thi', '123', '0943225643'),\n" +
                    "('NgocHuong', 'Trần Thị Ngọc Hương', '123', '0911602532')\n";
    public static final String INSERT_LOAI_HANG =
            "INSERT INTO LoaiHang (tenLoai) values\n" +
                    "('Hàng mát'),\n" +
                    "('Hàng đông lạnh'),\n" +
                    "('Thực phẩm khô'),\n" +
                    "('Đồ uống'),\n" +
                    "('Bánh kẹo'),\n" +
                    "('Thuốc lá'),\n" +
                    "('Văn phòng phẩm'),\n" +
                    "('Hoá phẩm'),\n" +
                    "('Giấy và bông'),\n" +
                    "('Dệt may')";
    public static final String INSERT_HOA_DON =
            "INSERT INTO HoaDon(mahang, maND,tongTien,ngayLap,trangThai) values\n" +
                    "('1','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('3','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('4','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0),\n" +
                    "('2','NgocHai',' 130000','2021/10/10',0)";
    public static final String INSERT_HOA_DON_CHI_TIET =
            "INSERT INTO HoaDonChiTiet(mahang, soLuong,gia,ghiChu) values\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú'),\n" +
                    "('2','2','130000','Ghi Chú')";
}