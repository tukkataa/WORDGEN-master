/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.songkhla.document;

/**
 *
 * @author Petpilin
 */

import com.songkhla.wordgen.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.docx4j.XmlUtils;
import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class W58 {
    public static void w58(String cc) {
            Connection conn=null;
            conn=ConnectDatabase.connect();
            PreparedStatement pst=null;
            String ccYear;
             String casetype;
             String caseno;
             String PoliceStationName="";
             String KK="";
             String BK="";
             String ProvincProsecutor="";
             String TelStation="";
             String CriminalCourt ="";
             String THNumBook="";
             String RankPolice ="";
             String FirstName ="";
             String LastName ="";
             String Position ="";
           
             
             
            try {
//               
                    String sqlDataPoliceStation="SELECT * FROM PoliceStation";
                      Statement sp = conn.createStatement();
                  ResultSet rs=sp.executeQuery(sqlDataPoliceStation); 
                  while (rs.next()) {                    
                         PoliceStationName=rs.getString("PoliceStaionName");
                         KK=rs.getString("KK");
                         BK=rs.getString("BK");
                         ProvincProsecutor=rs.getString("ProvincProsecutor");
                         TelStation=rs.getString("TelStation");
                         THNumBook= rs.getString("THNumBook");
                         CriminalCourt= rs.getString("CriminalCourt");
                      }
            String sqlDataPolice="SELECT * FROM Police";
                      Statement sp1 = conn.createStatement();
                  ResultSet rs1=sp1.executeQuery(sqlDataPolice); 
                  while (rs1.next()) {                    
                         RankPolice =rs1.getString("RankPolice");
                         FirstName=rs1.getString("FirstName");
                         LastName=rs1.getString("LastName");
                         Position=rs1.getString("Position");
                      }
                  
                        String sql="select crimecase.*,InvestInformation.*\n" +
                              "from crimecase \n" +
                              "left join InvestInformation on crimecase.PoliceNameCase=InvestInformation.InvestId \n" +
                              "where crimecase.CaseId='"+cc+"'\n" +
                              "group by crimecase.CaseId";

                Statement st = conn.createStatement();
            ResultSet s=st.executeQuery(sql); 
                System.out.println(sql);
            while((s!=null) && (s.next()))
            {  String  cs =s.getString("crimecaseno");
               ccYear=s.getString("crimecaseyears");
               casetype =s.getString("casetype");
               caseno  =s.getString("crimecasenoyear");
                String Date="";
                String Month="";
                String Year="";
                
                
                SimpleDateFormat sdfstart ;
                Calendar  calstart = Calendar.getInstance();
                sdfstart = new SimpleDateFormat("d", new Locale("th", "TH"));  
               Date =sdfstart.format(calstart.getTime());
              
               sdfstart = new SimpleDateFormat("MMMM", new Locale("th", "TH"));  
               Month=sdfstart.format(calstart.getTime());
               
               sdfstart = new SimpleDateFormat("yyyy", new Locale("th", "TH"));  
               Year=sdfstart.format(calstart.getTime());
                 

                 JSONObject bookmarkvalue = new JSONObject();
//              
                bookmarkvalue.put("C1",Checknull(Date));
                bookmarkvalue.put("C01",Checknull(Month));
                bookmarkvalue.put("C001",Checknull(Checknull(Year)));
		bookmarkvalue.put("C2",Checknull(cs));
                bookmarkvalue.put("C3",Checknull(ccYear));
                bookmarkvalue.put("CC2",Checknull(caseno));
                bookmarkvalue.put("C38",Checknull(s.getString("Investigator_Number")));
                bookmarkvalue.put("S2",Checknull(PoliceStationName).substring(10));
                bookmarkvalue.put("S7", Checknull(KK));
                bookmarkvalue.put("S8", Checknull(BK));
                bookmarkvalue.put("S29", Checknull(THNumBook));
                
                
                            bookmarkvalue.put("C4",(ToDate(s.getString("OccuredDate"))));
                            bookmarkvalue.put("C441", ReplaceCollon(s.getString("OccuredTime")));
                            bookmarkvalue.put("C12", Checknull(s.getString("CrimeLocationDistrict")));
                            bookmarkvalue.put("C5", Checknull(ToDate(s.getString("CaseAcceptDate"))));
                            bookmarkvalue.put("C551", ReplaceCollon(s.getString("CaseAccepTime")));
                            bookmarkvalue.put("C6",(ToDate(s.getString("CaseRequestDate"))));
                            bookmarkvalue.put("C15", Checknull(s.getString("DailyNumber")));
                                /*
                                bookmarkvalue.put("P02", Checknull(RankPolice));
                                bookmarkvalue.put("P03", Checknull(FirstName));
                                bookmarkvalue.put("P04", Checknull(LastName));
                                bookmarkvalue.put("P05", Checknull(Position));
                    */
                                 bookmarkvalue.put("P02", Checknull(s.getString("InvestRank")));
                        bookmarkvalue.put("P03", Checknull(s.getString("InvestName")));
                        bookmarkvalue.put("P04", "");
                        bookmarkvalue.put("P05", Checknull(s.getString("InvestPosition")));
                        bookmarkvalue.put("P012", Checknull(s.getString("InvestRankFull"))); //ยศเต็ม
                        bookmarkvalue.put("P013", Checknull(s.getString("InvestPosition"))); //ตำแหน่งเต็ม
    
			JSONArray tablecolumn = new JSONArray();
			tablecolumn.add("C2");
			tablecolumn.add("C3");
//			tablecolumn.add("SUSPECT");
//			tablecolumn.add("VICTIM");
//			tablecolumn.add("REMARK");
			JSONArray table1 = new JSONArray();
			JSONObject row1 = new JSONObject();
			row1.put("C2",cs);
			row1.put("C3", ccYear);
//			row1.put("SUSPECT", "period1");
//			row1.put("VICTIM", "period1");
//			row1.put("REMARK", "period1");
			table1.add(row1);
			
//			JSONObject repl2 = new JSONObject();
//			repl2.put("CRIMESNO", "function1");
//			repl2.put("DESCRIPTION", "desc1");
//			repl2.put("SUSPECT", "period1");
//			repl2.put("VICTIM", "period1");
//			repl2.put("REMARK", "period1");
//			table1.add(repl2);
		JSONObject tableobj = new JSONObject();
		tableobj.put("COLUMNS", tablecolumn);
		tableobj.put("TABLEDATA", table1);
			
		JSONArray TABLES = new JSONArray();
		TABLES.add(tableobj);
		bookmarkvalue.put("TABLES", TABLES);
		System.out.println("e01 :"+bookmarkvalue.toJSONString());
		
		
		try {
                  
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(new java.io.File("./TEMPLATE/w58.docx"));
                        
			processVariable(bookmarkvalue,wordMLPackage);
			processTABLE(bookmarkvalue,wordMLPackage);
			wordMLPackage.save(new java.io.File("./สำนวนอิเล็กทรอนิกส์"+"/"+PoliceStationName+"/ปี"+ccYear+"/"+casetype+"/"+casetype+cs+"-"+ccYear+"/ตำหนิรูปพรรณทรัพย์หายได้คืน "+cs+"-"+ccYear+".doc"));
		}catch( Exception ex) {
			ex.printStackTrace();
		}
                
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
              
	}
public static void nw58() {
     

                 JSONObject bookmarkvalue = new JSONObject();
//              
                bookmarkvalue.put("C1","");
                bookmarkvalue.put("C01","");
                bookmarkvalue.put("C001","");
		bookmarkvalue.put("C2","");
                bookmarkvalue.put("C3","");
                bookmarkvalue.put("CC2","");
                bookmarkvalue.put("C38","");
                bookmarkvalue.put("S2","");
                bookmarkvalue.put("S7", "");
                bookmarkvalue.put("S8", "");
                bookmarkvalue.put("S27","");
                bookmarkvalue.put("S10","");
                bookmarkvalue.put("S29","");
               

                            bookmarkvalue.put("C4","");
                            bookmarkvalue.put("C441", "");
                            bookmarkvalue.put("C12", "");
                            bookmarkvalue.put("C5", "");
                            bookmarkvalue.put("C551", "");
                            bookmarkvalue.put("C6","");
                            bookmarkvalue.put("C15", "");
                                
                        bookmarkvalue.put("P02", "");
                        bookmarkvalue.put("P03", "");
                        bookmarkvalue.put("P04", "");
                        bookmarkvalue.put("P05", "");
                        bookmarkvalue.put("P012", "");
                        bookmarkvalue.put("P013", "");
                    
    
		
		try {
                  
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(new java.io.File("./TEMPLATE/w58.docx"));
                        
			processVariable(bookmarkvalue,wordMLPackage);
			
			wordMLPackage.save(new java.io.File("./สำนวนอิเล็กทรอนิกส์/แบบฟอร์มสำนวน/ตำหนิรูปพรรณทรัพย์หายได้คืน.doc"));
		}catch( Exception ex) {
			ex.printStackTrace();
		}
                
            }
	public static void processVariable(JSONObject inputdata,WordprocessingMLPackage wordMLPackage) throws Exception {
		Object KEYSET[] = inputdata.keySet().toArray();
		Map<DataFieldName, String> map = new HashMap<DataFieldName, String>();
		for(int i=0;i<KEYSET.length;i++) {
			String KEY = (String)KEYSET[i];
			if(KEY.equals("TABLES")) { continue; }
			map.put(new DataFieldName(KEY), inputdata.get(KEY)+"");
                        System.out.println(KEY+"/"+inputdata.get(KEY)+"");
		}
		org.docx4j.model.fields.merge.MailMerger.performMerge(wordMLPackage, map, true);
	}
	
	public static void processTABLE(JSONObject inputdata,WordprocessingMLPackage wordMLPackage) throws Exception {
		

		JSONArray TABLES = (JSONArray)inputdata.get("TABLES");
		if(TABLES!=null) {
			for(int i=0;i<TABLES.size();i++) {
				JSONObject table  =(JSONObject)TABLES.get(i);
				if(table.get("COLUMNS")==null) {
					System.out.println("FOUND NULL COLUMNS");
					continue;
				}
				if(table.get("TABLEDATA")==null) {
					System.out.println("FOUND NULL TABLEDATA");
					continue;
				}
				replaceTable((JSONArray)table.get("COLUMNS"), (JSONArray)table.get("TABLEDATA"), wordMLPackage);
			}
		}else {
			System.out.println("FOUND NULL TABLES");
		}

	}
	
	
	
	private static Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}
	
	private static void addRowToTable(Tbl reviewtable, Tr templateRow, JSONObject datarow) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {
			Text text = (Text) object;
			String replacementValue = (String) datarow.get(text.getValue());
			if (replacementValue != null) {
				text.setValue(replacementValue);
			}
		}

		reviewtable.getContent().add(workingRow);
	}
	
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();
		
		if (obj==null) {
			return result;
		}
		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}
		}
		return result;
	}
        
	private static void replaceTable(JSONArray placeholders, JSONArray data,
			WordprocessingMLPackage template) throws Docx4JException, JAXBException {
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, (String)placeholders.get(0));
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);

		// first row is header, second row is content
		if (rows.size() == 2) {
			// this is our template row
			System.out.println("Found Table!! "+(String)placeholders.get(0));
			Tr templateRow = (Tr) rows.get(1);
			
			for(int i=0;i<data.size();i++) {
				// 2 and 3 are done in this method
				JSONObject datarow=(JSONObject)data.get(i);
				addRowToTable(tempTable, templateRow, datarow);
			}

			// 4. remove the template row
			tempTable.getContent().remove(templateRow);
		}
	}
         private static String ToDate(String strDate){
               String ResultDate="";
         try {
    	        if(strDate==null||strDate.equals("")||strDate.equals("null")) { return ""; }else{
    	       SimpleDateFormat df = new SimpleDateFormat("d/MM/yyyy", new Locale("th", "TH"));  
               SimpleDateFormat dateto  = new SimpleDateFormat("d MMMM yyyy", new Locale("th", "TH"));  
               Date date=null;
               
               date = df.parse(strDate);               
               ResultDate=dateto.format(date.getTime());}
         } catch (ParseException ex) {
             Logger.getLogger(W58.class.getName()).log(Level.SEVERE, null, ex);
         }
               return ResultDate;
}
         public static String Checknull(String input){
					if(input==null||input==""||input=="null") { return ""; }
					return getThaiNumber(input);
					}
           public static String ReplaceCollon(String inputTime){
                                        if(inputTime==null||inputTime==""||inputTime=="null") { return ""; }
					return  getThaiNumber(inputTime.replaceAll(":", "."));
					}
    private static String getThaiNumber(String amount) {  
        if(amount == null || amount.isEmpty()) return "";
        String[] DIGIT_TH = { "๐", "๑", "๒", "๓", "๔", "๕", "๖", "๗", "๘", "๙" };
        StringBuilder sb = new StringBuilder();
        for(char c : amount.toCharArray()){
            if(Character.isDigit(c)){
                String index = DIGIT_TH[Character.getNumericValue(c)].toString();
                sb.append(index);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();  
    }  
}