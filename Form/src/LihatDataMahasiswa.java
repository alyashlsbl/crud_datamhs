
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class LihatDataMahasiswa extends JFrame{
    
    String[][] data = new String [500][3]; //menampilkan ke bentuk array. per satu dari 4 itu isinya 500. [4] menandakan jumlah kolom
    String[] kolom = {"nim","nama","alamat"}; //nama kolom
    
    JTable tabel;
    JButton btnBack;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;
    
    public void LihatData(){
        setTitle("Data Mahasiswa");
        try {
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();
            
            String sql = "SELECT * FROM data_mhs"; //perintah mengambil data
            resultSet = statement.executeQuery(sql); //data disimpan dalam resultset
            
            int p=0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("nim");
                data[p][1] = resultSet.getString("nama");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
            koneksi.getKoneksi().close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan : " + sqle);
        } catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "Class tidak ditemukan : " + classe);
        }
        btnBack = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new formmhs();
            }
        });
        
    }
}
