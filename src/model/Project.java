package model;

import model.sqlite.ProjectSQLite;

import java.util.Date;
import java.util.List;

public class Project {

    private Integer id;

    private String client;
    private boolean banner;
    private boolean site;
    private boolean seo;
    private boolean grap;
    private String title;
    private Date order;
    private Date delivery;
    private String boss;
    private String responsible;
    private String description;

    public Project(String client, boolean banner, boolean site, boolean seo, boolean grap,String title, Date order, Date delivery, String boss,String responsible, String description) {
        this.client = client;
        this.banner = banner;
        this.site = site;
        this.seo = seo;
        this.grap = grap;
        this.title = title;
        this.order = order;
        this.delivery = delivery;
        this.boss = boss;
        this.responsible = responsible;
        this.description = description;
    }

    public Project(Integer id, String client,boolean banner, boolean site, boolean seo, boolean grap, String title, Date order, Date delivery,String boss, String responsible, String description) {
        this.id = id;
        this.client = client;
        this.banner = banner;
        this.site = site;
        this.seo = seo;
        this.grap = grap;
        this.title = title;
        this.order = order;
        this.delivery = delivery;
        this.boss = boss;
        this.responsible = responsible;
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getOrder() {
        return order;
    }

    public void setOrder(Date order) {
        this.order = order;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBanner() {
        return banner;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }

    public boolean isSite() {
        return site;
    }

    public void setSite(boolean site) {
        this.site = site;
    }

    public boolean isSeo() {
        return seo;
    }

    public void setSeo(boolean seo) {
        this.seo = seo;
    }

    public boolean isGrap() {
        return grap;
    }

    public void setGrap(boolean grap) {
        this.grap = grap;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    @Override
    public String toString(){
        return "Projeto: " + title + "\t\t\t\t\t" + "Cliente: " + client;
    }

    //--------- DAO

    private static ProjectSQLite dao = new ProjectSQLite();

    public void save(){
        if(id != null && dao.find(id) != null)
            dao.update(this);
        else
            dao.create(this);
    }

    public void delete(){
        if(dao.find(id) != null)
            dao.delete(this);
    }

    public static List<Project> all(){
        return dao.all();
    }

    public static Project find(int pk){
        return dao.find(pk);
    }
}
