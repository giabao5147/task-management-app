package com.example.quan_ly_cong_viec.controller.duan;

import com.example.quan_ly_cong_viec.dao.DuAnDAO;
import com.example.quan_ly_cong_viec.dao.NguoiDungDAO;
import com.example.quan_ly_cong_viec.model.DuAn;
import com.example.quan_ly_cong_viec.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/projects/show"})
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DuAnDAO duAnDAO = new DuAnDAO();
        AuthUtil authUtil = new AuthUtil();
        DuAn duAn = null;

        int id = Integer.parseInt(request.getParameter("id"));
        duAn = duAnDAO.getDuAn(id);
        request.setAttribute("duAn", duAn);
        request.setAttribute("tienDo", duAn.getTienDo().toString());
        request.setAttribute("danhSachThongTinDuAn", duAn.getDanhSachThongTinDuAn());
        request.setAttribute("soNhanVien", duAn.getDanhSachThongTinDuAn().size()+1);

        String role = authUtil.getRole(request);
        switch (role) {
            case "Admin":
                request.getRequestDispatcher("../admin/views/project/show.jsp").forward(request, response);
                break;
            case "Leader":
                request.getRequestDispatcher("../leader/views/project/show.jsp").forward(request, response);
                break;
        }

    }

}
