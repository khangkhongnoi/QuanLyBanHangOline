package com.YameShop.Controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.YameShop.domain.Supplier;
import com.YameShop.service.SupplierService;
 
@Controller
@RequestMapping("admin")
public class UserController {
 
    @Autowired
    private SupplierService supplierService;
     
     
    @GetMapping("export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=DanhSachNhaCungCap_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Supplier> listUsers = supplierService.findAll();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }  
 
}
