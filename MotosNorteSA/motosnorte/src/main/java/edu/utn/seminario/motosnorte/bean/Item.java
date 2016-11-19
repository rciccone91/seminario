package edu.utn.seminario.motosnorte.bean;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

import edu.utn.seminario.motosnorte.domain.Repuesto;
 
@ManagedBean(name = "item")
@SessionScoped
public class Item implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Repuesto repuesto;
    DetalleRepuesto order;
 
    public DetalleRepuesto getOrder() {
        return order;
    }
 
    public void setOrder(DetalleRepuesto order) {
        this.order = order;
    }
    private static final ArrayList<DetalleRepuesto> orderList = new ArrayList<DetalleRepuesto>();
 
    public ArrayList<DetalleRepuesto> getOrderList() {
        return orderList;
    }
 
    
    public String addAction() {
        DetalleRepuesto orderitem = new DetalleRepuesto(this.cantidad,this.repuesto);
        orderList.add(orderitem);
 
        return null;
    }
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Edited",((DetalleRepuesto) event.getObject()).getRepuesto().getModelo());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        orderList.remove((DetalleRepuesto) event.getObject());
    }

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}  
}