package Endpoints;

public class Routes {
	
	public static String base_url = "https://api.trello.com/1";
	
	//Board module
	
	public static String board_get_url= base_url+"/boards/{id}";
	public static String board_post_url= base_url+"/boards/";
	public static String board_put_url= base_url+"/boards/{id}";
	public static String board_delete_url= base_url+"/boards/{id}";
	public static String board_Invalid_url=base_url+"/bods/{id}";
	
	//Label module
	
	public static String label_get_url= base_url+"/label/{id}";
	public static String label_post_url= base_url+"/label/";
	public static String label_put_url= base_url+"/label/{id}";
	public static String label_delete_url= base_url+"/label/{id}";
	public static String label_Invalid_url=base_url+"//{id}";
}
