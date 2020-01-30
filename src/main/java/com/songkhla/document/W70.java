/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.songkhla.document;

/**
 *
 * @author Computer
 */
import com.songkhla.wordgen.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

public class W70 {
public static void w70(String cc,String noperson) {
            Connection conn=null;
            conn=ConnectDatabase.connect();
            PreparedStatement pst=null;
            
            String ccYear;
            String casetype;
            String caseno;
            String suspectFullNamePerson="";
             String PoliceStationName="";
             String HeadName="";
             String HeadPosition="";
             String ProvincProsecutor="";
             String TelStation="";
             String RankPolice ="";
             String FirstName ="";
             String LastName ="";
             String Position ="";
             int BailAssetId=0;
             int SumValue=0;
            try {
                
                 String sqlDataPoliceStation="SELECT * FROM PoliceStation";
                      Statement sp = conn.createStatement();
                  ResultSet rs=sp.executeQuery(sqlDataPoliceStation); 
                  while (rs.next()) {                    
                         PoliceStationName=rs.getString("PoliceStaionName");
                         HeadName=rs.getString("HeadName");
                         HeadPosition=rs.getString("HeadPosition");
                         ProvincProsecutor=rs.getString("ProvincProsecutor");
                         TelStation=rs.getString("TelStation");
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
//                

                   String sql="select crimecase.*,Person.PeopleRegistrationID suspectPeopleRegistrationID,Person.IssueDate suspectIssueDate,Person.FullNamePerson suspectFullNamePerson,Person.Age suspectAge,\n" +
                                "Person.Race suspectRace,Person.Nationality suspectNationality,Person.Religion suspectReligion,Person.IssuedBy suspectIssuedBy,\n" +
                                "Person.Occupation suspectOccupation,Person.HouseNumber suspectHouseNumber,Person.Moo suspectMoo,Person.Tambon suspectTambon\n" +
                                ",Person.Amphur suspectAmphur,Person.Province suspectProvince,Person.Soi suspectSoi,Person.SusConfress suspectSusConfress,P2.*,ChargeCase.*,BailAsset.*,InvestInformation.*\n" +
                                "from Person\n" +
                                "inner join( \n" +
                                "SELECT Person.*\n" +
                                "FROM Person where Person.Related='นายประกัน' and Person.caseIdPerson='"+cc+"' and Person.ownerbail='"+noperson+"')P2 \n" +
                                "left join CrimeCase on Person.caseidperson=CrimeCase.caseid\n" +
                                "left join ChargeCase on crimecase.Caseid=ChargeCase.ChargeCaseid\n" +
                                "left join BailAsset on Person.noperson = BailAsset.BailpersonId\n" +
                                "left join InvestInformation on crimecase.PoliceNameCase=InvestInformation.InvestId \n" +
                                "where Person.StatusBail='ประกัน' and CrimeCase.caseid='"+cc+"' and Person.noperson='"+noperson+"'";

                Statement st = conn.createStatement();
            ResultSet s=st.executeQuery(sql); 
                System.out.println(sql);
                String VarBA3 = null;
            while((s!=null) && (s.next()))
            {  String  cs =s.getString("crimecaseno");
                 ccYear=s.getString("crimecaseyears");
                 casetype =s.getString("casetype");
                 caseno  =s.getString("crimecasenoyear");
                 suspectFullNamePerson= s.getString("suspectFullNamePerson");
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
                 
//                System.out.print("ข้อหา :: "+s.getString("ChargeCode"));
//                System.out.print(" - ");
                 JSONObject bookmarkvalue = new JSONObject();
//              
                bookmarkvalue.put("C1",Checknull(Date));
                bookmarkvalue.put("C01",Checknull(Month));
                bookmarkvalue.put("C001",Checknull(Year));
		bookmarkvalue.put("C2",Checknull(cs));
                bookmarkvalue.put("C3",Checknull(ccYear));
                bookmarkvalue.put("CC2",Checknull(caseno));
                bookmarkvalue.put("S2",Checknull(PoliceStationName).substring(10));
                bookmarkvalue.put("S02",Checknull(PoliceStationName));
                bookmarkvalue.put("S27",Checknull(ProvincProsecutor));
                bookmarkvalue.put("S13",Checknull(HeadName));
                bookmarkvalue.put("S14",Checknull(HeadPosition));
                   
//                   ----------------------------ผู้กล่าวหา--------------------
               
                bookmarkvalue.put("PA7",Checknull(s.getString("AccureandOther")));
               
                
                
//                   ----------------------------ผู้ต้องหา--------------------
                    bookmarkvalue.put("PS2", Checknull(s.getString("suspectPeopleRegistrationID"))); 
                    bookmarkvalue.put("PS3",Checknull(ToDate(s.getString("suspectIssueDate")))); 
                    bookmarkvalue.put("PS5",Checknull(s.getString("suspectIssuedBy"))); 
                    bookmarkvalue.put("PS7", Checknull(suspectFullNamePerson)); 
                    System.out.print("ชื่อผู้ต้องหา"+suspectFullNamePerson);
                    bookmarkvalue.put("PS13", Checknull(s.getString("suspectAge")));
                    bookmarkvalue.put("PS14", Checknull(s.getString("suspectRace")));
                    bookmarkvalue.put("PS15", Checknull(s.getString("suspectNationality")));
                    bookmarkvalue.put("PS16", Checknull(s.getString("suspectReligion")));
                    bookmarkvalue.put("PS17", Checknull(s.getString("suspectOccupation")));
                    bookmarkvalue.put("PS22", Checknull(s.getString("suspectHouseNumber")));
                    bookmarkvalue.put("PS23", Checknull(s.getString("suspectMoo")));
                    bookmarkvalue.put("PS24", Checknull(s.getString("suspectTambon")));
                    bookmarkvalue.put("PS25", Checknull(s.getString("suspectAmphur")));
                    bookmarkvalue.put("PS26", Checknull(s.getString("suspectProvince")));
                    bookmarkvalue.put("PS105", Checknull(s.getString("suspectSoi")));
                    bookmarkvalue.put("PS108", Checknull(s.getString("suspectSusConfress")));
                    
                bookmarkvalue.put("PB7",  Checknull(s.getString("FullNamePerson")));
                bookmarkvalue.put("PB13", Checknull(s.getString("Age")));
                bookmarkvalue.put("PB14", Checknull(s.getString("Race")));
                bookmarkvalue.put("PB15", Checknull(s.getString("Nationality")));
                bookmarkvalue.put("PB22", Checknull(s.getString("HouseNumber"))); 
                bookmarkvalue.put("PB23", Checknull(s.getString("Moo"))); 
                bookmarkvalue.put("PB24", Checknull(s.getString("Tambon"))); 
                bookmarkvalue.put("PB25", Checknull(s.getString("Amphur"))); 
                bookmarkvalue.put("PB26", Checknull(s.getString("Province"))); 
                bookmarkvalue.put("PB104", Checknull(s.getString("Road")));
                bookmarkvalue.put("PB105", Checknull(s.getString("Soi")));
                    
                     

                      bookmarkvalue.put("B2", Checknull(s.getString("ChargeNameCase")));
                      bookmarkvalue.put("B3", Checknull(s.getString("LawCase")));
                      bookmarkvalue.put("B4", Checknull(s.getString("RateOfPenaltyCase")));
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
                        
                            bookmarkvalue.put("C4",Checknull(ToDate(s.getString("OccuredDate"))));
                            bookmarkvalue.put("C441", ReplaceCollon(s.getString("OccuredTime")));
                            bookmarkvalue.put("C5", Checknull(s.getString("CaseAcceptDate")));
                            bookmarkvalue.put("C551", ReplaceCollon(s.getString("CaseAccepTime")));
                            bookmarkvalue.put("C6", Checknull(ToDate(s.getString("CaseRequestDate"))));
                            bookmarkvalue.put("C661", ReplaceCollon(s.getString("CaseRequestTime")));
                            
                            bookmarkvalue.put("C8", Checknull(s.getString("CrimeLocation")));
                            bookmarkvalue.put("C11", Checknull(s.getString("CrimeLocationRoad")));
                            bookmarkvalue.put("C12", Checknull(s.getString("CrimeLocationDistrict")));
                            bookmarkvalue.put("C13", Checknull(s.getString("CrimeLocationAmphur")));
                            bookmarkvalue.put("C14", Checknull(s.getString("CrimeLocationProvince")));
                            bookmarkvalue.put("C15", Checknull(s.getString("DailyNumber")));
                            
                String[]   BailAssetTotal1 = s.getString("BailAssetTotal").split(" ");
                System.out.println(">>>>>"+Arrays.toString(BailAssetTotal1));
                String a=BailAssetTotal1[0];
                
                String   BailAssetTotal = a.replace (",", "");
               System.out.println(">>>>>"+BailAssetTotal);
                   
                 if ((BailAssetTotal) != null)
                    {
                    
                   SumValue = SumValue+Integer.parseInt(BailAssetTotal);
                    } 
                      ++BailAssetId ;
                            VarBA3= VarBA3+","+s.getString("BailAssetDetail");
                            bookmarkvalue.put("AB3","หลักทรัพย์ที่ใช้ประกัน "+Checknull(VarBA3).substring(5)+" ราคาประเมิน "+Checknull(regexCommafy(Integer.toString(SumValue)))+" บาท");
                            
  
			JSONArray tablecolumn = new JSONArray();

			JSONArray table1 = new JSONArray();
			JSONObject row1 = new JSONObject();

			table1.add(row1);
			
		JSONObject tableobj = new JSONObject();

		System.out.println(bookmarkvalue.toJSONString());
		
		
		try {
                  
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(new java.io.File("./TEMPLATE/w70.docx"));
			processVariable(bookmarkvalue,wordMLPackage);
			processTABLE(bookmarkvalue,wordMLPackage);
			wordMLPackage.save(new java.io.File("./สำนวนอิเล็กทรอนิกส์"+"/"+PoliceStationName+"/ปี"+ccYear+"/"+casetype+"/"+casetype+cs+"-"+ccYear+"/บันทึกการเสนอสัญญาประกัน "+suspectFullNamePerson+"" +cs+"-"+ccYear+".doc"));
		}catch( Exception ex) {
			ex.printStackTrace();
		}
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
              
	}
public static void nw70() {
 
                 JSONObject bookmarkvalue = new JSONObject();
//              
                bookmarkvalue.put("C1","");
                bookmarkvalue.put("C01","");
                bookmarkvalue.put("C001","");
		bookmarkvalue.put("C2","");
                bookmarkvalue.put("C3","");
                bookmarkvalue.put("CC2","");
                bookmarkvalue.put("S2","");
                bookmarkvalue.put("S02","");
                bookmarkvalue.put("S27","");
                bookmarkvalue.put("S13","");
                bookmarkvalue.put("S14","");
                   
//                   ----------------------------ผู้กล่าวหา--------------------
               
                bookmarkvalue.put("PA7","");
               
                
                
//                   ----------------------------ผู้ต้องหา--------------------
                    bookmarkvalue.put("PS2", ""); 
                    bookmarkvalue.put("PS3",""); 
                    bookmarkvalue.put("PS5",""); 
                    bookmarkvalue.put("PS7", ""); 
                    bookmarkvalue.put("PS13","");
                    bookmarkvalue.put("PS14", "");
                    bookmarkvalue.put("PS15", "");
                    bookmarkvalue.put("PS16", "");
                    bookmarkvalue.put("PS17", "");
                    bookmarkvalue.put("PS22", "");
                    bookmarkvalue.put("PS23", "");
                    bookmarkvalue.put("PS24", "");
                    bookmarkvalue.put("PS25", "");
                    bookmarkvalue.put("PS26", "");
                    bookmarkvalue.put("PS105","");
                    bookmarkvalue.put("PS108","");
                    
                bookmarkvalue.put("PB7", "");
                bookmarkvalue.put("PB13", "");
                bookmarkvalue.put("PB14", "");
                bookmarkvalue.put("PB15", "");
                bookmarkvalue.put("PB22", ""); 
                bookmarkvalue.put("PB23", ""); 
                bookmarkvalue.put("PB24", ""); 
                bookmarkvalue.put("PB25", ""); 
                bookmarkvalue.put("PB26", ""); 
                bookmarkvalue.put("PB104", ""); 
                bookmarkvalue.put("PB105", ""); 

                      bookmarkvalue.put("B2", "");
                      bookmarkvalue.put("B3", "");
                      bookmarkvalue.put("B4", "");
                      
                       bookmarkvalue.put("P02", "");
                        bookmarkvalue.put("P03", "");
                        bookmarkvalue.put("P04", "");
                        bookmarkvalue.put("P05", "");
                        bookmarkvalue.put("P012", "");
                        bookmarkvalue.put("P013", "");
                    
                            bookmarkvalue.put("C4","");
                            bookmarkvalue.put("C441","");
                            bookmarkvalue.put("C5", "");
                            bookmarkvalue.put("C551","");
                            bookmarkvalue.put("C6", "");
                            bookmarkvalue.put("C661", "");
                            
                            bookmarkvalue.put("C8", "");
                            bookmarkvalue.put("C11", "");
                            bookmarkvalue.put("C12", "");
                            bookmarkvalue.put("C13", "");
                            bookmarkvalue.put("C14", "");
                            bookmarkvalue.put("C15", "");
                    
                            bookmarkvalue.put("AB3", "");
          
		try {
                  
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(new java.io.File("./TEMPLATE/w70.docx"));
			processVariable(bookmarkvalue,wordMLPackage);
			
			wordMLPackage.save(new java.io.File("./สำนวนอิเล็กทรอนิกส์/แบบฟอร์มสำนวน/บันทึกการเสนอสัญญาประกัน.doc"));
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
             Logger.getLogger(W70.class.getName()).log(Level.SEVERE, null, ex);
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
    private static String regexCommafy(String inputNum)
    {
        String regex = "(\\d)(?=(\\d{3})+$)";
        String [] splittedNum = inputNum.split("\\.");
        if(splittedNum.length==2)
        {
            return splittedNum[0].replaceAll(regex, "$1,")+"."+splittedNum[1];
        }
        else
        {
            return inputNum.replaceAll(regex, "$1,");
        }
    }
}
