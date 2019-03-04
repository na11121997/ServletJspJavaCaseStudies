package com.cg.billing.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.billing.beans.Plan;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.BillingServices;
import com.cg.billing.services.BillingServicesImpl;

@WebServlet("/planDetails")
public class PlanDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BillingServices services;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Plan plan=services.getCustomerPostPaidAccountPlanDetails(Integer.parseInt(request.getParameter("customerId")), Long.parseLong(request.getParameter("mobileNumber")));
		    request.setAttribute("plan", plan);
		    request.getRequestDispatcher("getPlanDetailsPage.jsp").forward(request, response);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException
				| PlanDetailsNotFoundException e) {
			request.setAttribute("exception", e.getMessage());
		    request.getRequestDispatcher("getPlanDetailsPage.jsp").forward(request, response);
		}
	}
	@Override
	public void destroy() {
		services=null;
		super.destroy();
	}
	@Override
	public void init() throws ServletException {
		services=new BillingServicesImpl();
		super.init();
	}
	

}
