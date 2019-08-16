package edu.ustb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ustb.domain.Rt;
import edu.ustb.service.RtService;
import edu.ustb.service.impl.RtServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rt/*")
public class RtController extends BaseServlet {
    private RtService service = new RtServiceImpl();

    public void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("cid");
        int cid = 0;
        if (id != null && !id.equals("") && !id.equals("null")) {
            cid = Integer.parseInt(id);
        }
        List<Rt> rtList = service.find(cid);
        writeValue(rtList, response);
    }
}
