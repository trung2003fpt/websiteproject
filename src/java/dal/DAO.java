/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import model.Category;
import model.Comment;
import model.User;
import model.dem;

/**
 *
 * @author Nam
 */
public class DAO extends DBContext {

    public Category getCategoryById(int id) {
        String sql = "Select * from Categories where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println("getCategoryById: " + e.getMessage());
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "Select * from Users where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getBoolean("role"), rs.getString("name"),
                        rs.getString("account"), rs.getString("password"), rs.getString("email"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("getUserById: " + e.getMessage());
        }
        return null;
    }

    public Comment getCommentById(int id) {
        String sql = "Select * from Comments where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                User u = getUserById(rs.getInt("UserID"));
                Article a = getArticleById(rs.getInt("ArticleID"));
                Comment c = new Comment(rs.getInt("id"), rs.getString("content"), rs.getDate("postDate"), u, a);
                return c;
            }
        } catch (SQLException e) {
            System.out.println("getCommentById: " + e.getMessage());
        }
        return null;
    }

    public Article getArticleById(int id) {
        String sql = "Select * from Articles where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                return a;
            }
        } catch (SQLException e) {
            System.out.println("getArticleById: " + e.getMessage());
        }
        return null;
    }
    public Article getArticleJustAdd() {
        String sql = "select top 1 * from articles order by id desc";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                return a;
            }
        } catch (SQLException e) {
            System.out.println("getArticleJustAdd: " + e.getMessage());
        }
        return null;
    }
    
    public List<Article> getLatestArticle() {
        List<Article> articles = new ArrayList<>();
        String sql = "select * from Articles order by PostDate desc";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getLatestArticle: " + e.getMessage());
        }
        return articles;
    }

    public List<Article> getMostViewArticle() {
        List<Article> articles = new ArrayList<>();
        String sql = "select a.*,d.dem from articles a join dem d on a.id = d.articleid order by d.dem desc";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getLatestArticle: " + e.getMessage());
        }
        return articles;
    }
    
    public List<Article> getAllArticleSortByCname() {
        List<Article> articles = new ArrayList<>();
        String sql = "select * from articles a join categories c on a.categoryID = c.id order by c.name";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAllArticleByCid: " + e.getMessage());
        }
        return articles;
    }


    public List<Article> getAllArticleByCid(int cid) {
        List<Article> articles = new ArrayList<>();
        String sql = "Select * from Articles where Categoryid=? order by PostDate desc";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, cid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAllArticleByCid: " + e.getMessage());
        }
        return articles;
    }
    public List<Article> getMostViewArticleByCid(int cid) {
        List<Article> articles = new ArrayList<>();
        String sql = "select a.*,d.dem from articles a join dem d on a.id = d.articleid where CategoryID=? order by d.dem desc";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, cid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getMostViewArticleByCid: " + e.getMessage());
        }
        return articles;
    }
    public List<Article> getAllLatestArticleByCid() {
        List<Article> articles = new ArrayList<>();
        String sql = "select top 3 * from Articles order by CategoryID asc";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAllLatestArticleByCid: " + e.getMessage());
        }
        return articles;
    }

    public List<Article> getAllArticleByUid(int uid) {
        List<Article> articles = new ArrayList<>();
        String sql = "Select * from Articles where UserID=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, uid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAllArticleByUid: " + e.getMessage());
        }
        return articles;
    }

    public List<Article> searchArticle(String title) {
        List<Article> articles = new ArrayList<>();
        String sql = "select * from Articles where 1=1";
        if (title != null && (title.length() > 0)) {
            sql += " and title like N'%" + title + "%'";
        }
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Search: " + e.getMessage());
        }
        return articles;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "Select * from Categories";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                categories.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getAllCategory: " + e.getMessage());
        }
        return categories;
    }

    public List<dem> getAlldem() {
        List<dem> dems = new ArrayList<>();
        String sql = "Select * from dem";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Article a = getArticleById(rs.getInt("ArticleID"));
                dem d = new dem(rs.getInt("dem"), a);
                dems.add(d);
            }
        } catch (SQLException e) {
            System.out.println("getAllCategory: " + e.getMessage());
        }
        return dems;
    }

    public dem getDemById(int id) {
        String sql = "Select * from dem where articleID=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Article a = getArticleById(rs.getInt("ArticleID"));
                dem d = new dem(rs.getInt("dem"), a);
                return d;
            }
        } catch (SQLException e) {
            System.out.println("getAllCategory: " + e.getMessage());
        }
        return null;
    }

    public void updateDem(int aid) {
        String sql = "update Dem set dem=dem+1 where articleid=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, aid);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateArticle: " + e.getMessage());
        }
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        String sql = "Select * from Users";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getBoolean("role"), rs.getString("name"),
                        rs.getString("account"), rs.getString("password"), rs.getString("email"));
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("getAllUser: " + e.getMessage());
        }
        return users;
    }

    public List<User> getAllWriter() {
        List<User> users = new ArrayList<>();
        String sql = "Select * from Users where Role=1";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getBoolean("role"), rs.getString("name"),
                        rs.getString("account"), rs.getString("password"), rs.getString("email"));
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("getAllUser: " + e.getMessage());
        }
        return users;
    }

    public List<Comment> getAllComment() {
        List<Comment> comments = new ArrayList<>();
        String sql = "Select * from Comments";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = getUserById(rs.getInt("userID"));
                Article a = getArticleById(rs.getInt("ArticleID"));
                Comment c = new Comment(rs.getInt("id"), rs.getString("content"), rs.getDate("postDate"), u, a);
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getAllComment: " + e.getMessage());
        }
        return comments;
    }

    public List<Comment> getAllCommentbyAid(int aid) {
        List<Comment> comments = new ArrayList<>();
        String sql = "Select * from Comments where ArticleID=? order by PostDate desc";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, aid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = getUserById(rs.getInt("userID"));
                Article a = getArticleById(rs.getInt("ArticleID"));
                Comment c = new Comment(rs.getInt("id"), rs.getString("content"), rs.getDate("postDate"), u, a);
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getAllCommentbyAid: " + e.getMessage());
        }
        return comments;
    }

    public List<Comment> getAllCommentbyUid(int uid) {
        List<Comment> comments = new ArrayList<>();
        String sql = "Select * from Comments where UserID=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, uid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User u = getUserById(rs.getInt("userID"));
                Article a = getArticleById(rs.getInt("ArticleID"));
                Comment c = new Comment(rs.getInt("id"), rs.getString("content"), rs.getDate("postDate"), u, a);
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getAllCommentbyUid: " + e.getMessage());
        }
        return comments;
    }

    public List<Article> getAllArticle() {
        List<Article> articles = new ArrayList<>();
        String sql = "select * from Articles order by id";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category c = getCategoryById(rs.getInt("CategoryID"));
                User u = getUserById(rs.getInt("UserID"));
                Article a = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("image"),
                        rs.getDate("postDate"), u, c);
                articles.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAllArticle: " + e.getMessage());
        }
        return articles;
    }


    public void insertAnArticle(String title, String content, String Image, String postDate, int userId, int cateId) {
        String sql = "INSERT INTO Articles(Title, Content, Image, PostDate, UserID,CategoryID) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, title);
            pstm.setString(2, content);
            pstm.setString(3, Image);
            pstm.setString(4, postDate);
            pstm.setInt(5, userId);
            pstm.setInt(6, cateId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AddArticle: " + e.getMessage());
        }
    }
    
    public void insertViewForNewArticle(int aid) {
        String sql = "insert into dem(dem,ArticleID) values(0, ?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, aid);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertViewForNewArticle: " + e.getMessage());
        }
    }

    public void deleteAnArticle(int id) {
        String sql = "delete from Articles where ID=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DeleteArticle: " + e.getMessage());
        }
    }
    
    public void deleteDem(int id) {
        String sql = "delete from dem where ArticleID = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteDem: " + e.getMessage());
        }
    }

    public void deleteAComment(int id) {
        String sql = "delete from comments where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DeleteComment: " + e.getMessage());
        }
    }

    public void deleteAUser(int id) {
        String sql = "Delete from Users where Id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteAUser: " + e.getMessage());
        }
    }

    public void updateArticle(Article a) {
        String sql = "update Articles set Title=?,Content=?,Image=?,PostDate=?,UserID=?,CategoryID=? where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, a.getTitle());
            pstm.setString(2, a.getContent());
            pstm.setString(3, a.getImage());
            pstm.setDate(4, a.getPostDate());
            pstm.setInt(5, a.getUser().getId());
            pstm.setInt(6, a.getCategory().getId());
            pstm.setInt(7, a.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateArticle: " + e.getMessage());
        }
    }

    public void insertAUser(String name, String acc, String pass, boolean role, String email) {
        String sql = "INSERT INTO users (name, account, password, role, email) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, acc);
            pstm.setString(3, pass);
            pstm.setBoolean(4, role);
            pstm.setString(5, email);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AddUser: " + e.getMessage());
        }
    }

    public void updateUser(User u) {
        String sql = "update Users set name=?,account=?,password=?,email=? where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getAccount());
            pstm.setString(3, u.getPassword());
            pstm.setString(4, u.getEmail());
            pstm.setInt(5, u.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateArticle: " + e.getMessage());
        }
    }

    public void updateUser1(User u) {
        String sql = "update Users set name=?,account=?,password=?,email=?,Role=? where id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getAccount());
            pstm.setString(3, u.getPassword());
            pstm.setString(4, u.getEmail());
            pstm.setBoolean(5, u.isRole());
            pstm.setInt(6, u.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateUser1: " + e.getMessage());
        }
    }

    public void insertAnComment(String content, String postDate, int artiId, int userId) {
        String sql = "INSERT INTO Comments(Content,PostDate,ArticleID,UserID) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, content);
            pstm.setString(2, postDate);
            pstm.setInt(3, artiId);
            pstm.setInt(4, userId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AddComment: " + e.getMessage());
        }
    }

    public void updateComment(String content, int id) {
        String sql = "update Comments set Content=? where Id=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, content);
            pstm.setInt(2, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateArticle: " + e.getMessage());
        }
    }

    public boolean checkUser(String account, String password) {
        String strSelect = "select * from Users "
                + "where account=? "
                + "and password=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }
//
//    public String getNameByAccount(String account) {
//        String strSelect = "select * from Users "
//                + "where account=N'" + account + "'";
//        try {
//            PreparedStatement stm = connection.prepareStatement(strSelect);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString(2);
//                return name;
//            }
//        } catch (SQLException e) {
//            System.out.println("getNameByAccount:" + e.getMessage());
//        }
//        return "";
//    }

    public User getUserByAccount(String acc) {
        String sql = "Select * from Users where account=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, acc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getBoolean("role"), rs.getString("name"),
                        rs.getString("account"), rs.getString("password"), rs.getString("email"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("getUserByAccount: " + e.getMessage());
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "Select * from Users where Email=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getBoolean("role"), rs.getString("name"),
                        rs.getString("account"), rs.getString("password"), rs.getString("email"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("getUserByAccount: " + e.getMessage());
        }
        return null;
    }
}
