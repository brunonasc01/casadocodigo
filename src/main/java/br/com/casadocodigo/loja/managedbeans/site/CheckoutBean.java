package br.com.casadocodigo.loja.managedbeans.site;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.CheckoutDAO;
import br.com.casadocodigo.loja.daos.SystemUserDAO;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.SystemUser;

@Model
public class CheckoutBean {

	private SystemUser systemUser = new SystemUser();

	@Inject
	private SystemUserDAO systemUserDAO;

	@Inject
	private CheckoutDAO checkoutDAO;

	@Inject
	private ShoppingCart cart;

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {

		this.systemUser = systemUser;
	}

	@Transactional
	public void checkout() throws IOException {
		systemUserDAO.save(systemUser);
		//vamos também gravar a compra
		//aprovar com um sistema externo
		Checkout checkout = new Checkout(systemUser, cart);
		checkoutDAO.save(checkout);

		//criar o client para enviar request rest
	}

}
