package com.example.ph34050_pnlibary.database;

public class Data_SQLite {
    public static final String INSERT_THU_THU = "" +
            "Insert into ThuThu(MaTT,HoTen,MatKhau)values\n" +
            "('admin','Nguyen admin','admin'),\n" +
            "('thuthu1','dungtt','12345'),\n" +
            "('thuthu2','trangtt','12345')";

    public static final String INSERT_THANH_VIEN = "" +
            "Insert into ThanhVien(HoTen,NamSinh,cccd)values\n" +
            "('Nguyen Dinh Thi','2004','001204021558'),\n" +
            "('Nguyen Hoang Anh','2004','001204021558'),\n" +
            "('Nguyen Thu Thao','2004','02023938764'),\n" +
            "('Ngo Dinh Long','2004','001204021558'),\n" +
            "('Ha Van Dao','2004','02023938764'),\n" +
            "('Tran Quoc Sang','2004','001204021558'),\n" +
            "('Nguyen Van Cuong','2004','02023938764'),\n" +
            "('Nguyen Van Viet','2004','001204021558'),\n" +
            "('Bui Duc Long','2004','02023938764')";
    public static final String INSERT_LOAI_SACH = "" +
            "insert into LoaiSach(tenLoai)values\n" +
            "('Tieng anh'),\n" +
            "('Toán học nâng cao'),\n" +
            "('Lap trinh javascript'),\n" +
            "('Lap trinh web'),\n" +
            "('Lap trinh c')";
    public static final String INSERT_SACH = "" +
            "insert into Sach(TenSach,GiaThue,MaLoai) values\n" +
            "('Lập trình C nâng cao','5000','5'),\n" +
            "('Lâp trình C cơ bản','5000','5'),\n" +
            "('js nâng cao','5000','3'),\n" +
            "('Nhập môn lập trình C','5000','5'),\n" +
            "('Lập trình web với HTML5 và CSS','5000','4'),\n" +
            "('Thiết kế web với Bootstrap','5000','4'),\n" +
            "('js cơ bản','5000','3'),\n" +
            "('Toán học cao cấp','5000','2'),\n" +
            "('Tiếng anh cơ bản','5000','1'),\n" +
            "('Tiếng anh giao tiếp','5000','1')";
    public static final String INSERT_PHIEU_MUON = "" +
            "insert into PhieuMuon(MaTT,MaTV,MaSach,TienThue,Ngay,TraSach) values\n" +
            "('admin','1','1','5000','2021-1-17',0),\n" +
            "('thuthu1','2','4','5000','2023-09-19',1),\n" +
            "('admin','3','3','5000','2022-09-21',1),\n" +
            "('thuthu2','4','5','5000','2012-10-11',0),\n" +
            "('thuthu2','5','2','5000','2022-11-05',1),\n" +
            "('thuthu1','6','6','5000','2021-05-14',0)";
}
