package sit.int202.classsicmodels.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classsicmodels.entites.Product;
import sit.int202.classsicmodels.models.Cart;
import sit.int202.classsicmodels.models.ClassicModelLineItem;
import sit.int202.classsicmodels.repositories.ProductRepository;

import java.io.IOException;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        Cart<String, ClassicModelLineItem> cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart<>();
            session.setAttribute("cart", cart);
        }
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.find(productCode);
        if (product != null) {
            cart.addItem(productCode, new ClassicModelLineItem(product));
        }
        response.getWriter().print(cart.getQuantity());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
