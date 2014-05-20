package com.groupdocs.viewer.samples.javaweb;

import com.groupdocs.viewer.domain.FileId;
import com.groupdocs.viewer.domain.FilePath;
import com.groupdocs.viewer.domain.FileUrl;
import com.groupdocs.viewer.domain.GroupDocsPath;
import com.groupdocs.viewer.domain.TokenId;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex Bobkov
 */
public class IndexServlet extends ViewerServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");
        String filePath = request.getParameter("filePath");
        String tokenId = request.getParameter("tokenId");
        String fileUrl = request.getParameter("fileUrl");

        String initFilePath = "";
        GroupDocsPath groupdocsFilePath = null;
        if(fileId != null && !fileId.isEmpty()){
            groupdocsFilePath = new FileId(fileId);
        }else if(filePath != null && !filePath.isEmpty()){
            groupdocsFilePath = new FilePath(filePath, viewerHandler.getConfiguration());
        }else if(tokenId != null && !tokenId.isEmpty()){
            groupdocsFilePath = new TokenId(tokenId);
            //if(!tki.isExpired()){
                //groupdocsFilePath = tki;
            //}
        }else if(fileUrl != null && !fileUrl.isEmpty()){
            groupdocsFilePath = new FileUrl(fileUrl);
        }
        if(groupdocsFilePath != null){
            initFilePath = groupdocsFilePath.getPath();
        }
        
        request.setAttribute("filePath", initFilePath);
        request.setAttribute("viewer_head", viewerHandler.getHeader());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewer/index.jsp");
        requestDispatcher.forward(request, response);
    }

}
