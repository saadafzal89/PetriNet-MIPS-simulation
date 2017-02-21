/* On my honor, I have neither given nor received unauthorized aid on this assignment*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;


public class MIPSsim 
{
	static ArrayList<String> INM = new ArrayList<String>();
	static String[] INB = new String[4];
	static String[] LIB = new String[4];
	static String[] AIB = new String[4];
	static String[] ADB = new String[2];
	static ArrayList<String> REB = new ArrayList<String>();
	static ArrayList<String> RGF = new ArrayList<String>();
	static ArrayList<String> DAM = new ArrayList<String>();
	static HashMap<String, Integer> reg_hashMap = new HashMap<String, Integer>();
	static HashMap<Integer, Integer> data_hashMap = new HashMap<Integer, Integer>();
	static int counter=0;
	static int clear = 0;
	static BufferedWriter wr = null;
	public static void display()
	{
		String register =" ";
		String memory = " ";
		//System.out.println("STEP "+counter+":");
		
		Map<String, Integer> map = new TreeMap<String, Integer>(reg_hashMap);
		try
		{
			wr.write("STEP "+counter+":\n");
			//INM displayed
			String disp = INM.toString();
			disp = disp.substring(1,disp.length()-1);
			//System.out.println("INM: "+disp);
			wr.write("INM: "+disp+"\n");
			
			//INB displayed
			if(INB[0] != null)
			{
				//System.out.print("INB: <");
				wr.write("INB: <");
				for(int i=0;i<INB.length;i++)
				{
					if(i!=INB.length-1)
					{
						//System.out.print(INB[i]+",");
						wr.write(INB[i]+",");
					}
					else
					{
						//System.out.print(INB[i]);
						wr.write(INB[i]);
					}
				}
				//System.out.print(">");
				//System.out.println();
				wr.write(">\n");
			}
			else
			{
				//System.out.println("INB: ");
				wr.write("INB: \n");
			}	
			
			//AIB displayed
			if(AIB[0] != null)
			{
				//System.out.print("AIB: <");
				wr.write("AIB: <");
				for(int i=0;i<AIB.length;i++)
				{
					if(i!=AIB.length-1)
					{
						//System.out.print(AIB[i]+",");
						wr.write(AIB[i]+",");
					}
					else
					{
						//System.out.print(AIB[i]);
						wr.write(AIB[i]);
					}
				}
				//System.out.print(">");
				//System.out.println();
				wr.write(">\n");
			}
			else
			{
				//System.out.println("AIB: ");
				wr.write("AIB: \n");
			}
			
			
			//LIB displayed
			if(LIB[0] != null)
			{
				//System.out.print("LIB: <");
				wr.write("LIB: <");
				for(int i=0;i<LIB.length;i++)
				{
					if(i!=LIB.length-1)
					{
						//System.out.print(LIB[i]+",");
						wr.write(LIB[i]+",");
					}
					else
					{
						//System.out.print(LIB[i]);
						wr.write(LIB[i]);
					}
				}
				//System.out.print(">");
				//System.out.println();
				wr.write(">\n");
			}
			else
			{
				//System.out.println("LIB: ");
				wr.write("LIB: \n");
			}
			
			//ADB displayed
			if(ADB[0] != null)
			{
				//System.out.println("ADB: <"+ADB[0]+","+ADB[1]+">");
				wr.write("ADB: <"+ADB[0]+","+ADB[1]+">\n");
			}
			else
			{
				//System.out.println("ADB:");
				wr.write("ADB:\n");
			}
			
			//REB displayed
			String reb1 = REB.toString();
			reb1= reb1.substring(1, reb1.length()-1);
			//System.out.println("REB: "+reb1);
			wr.write("REB: "+reb1+"\n");
			
			Set reg_set = map.entrySet();
	        Iterator itr = reg_set.iterator();
	        while(itr.hasNext()) 
	        {
	             Map.Entry reg_map = (Map.Entry)itr.next();
	             register = register.concat("<"+reg_map.getKey()+","+reg_map.getValue()+">,");
	        }
	        register = register.substring(1,register.length()-1);
			//System.out.println("RGF: "+register); 
			wr.write("RGF: "+register+"\n");
			
			for (Integer name: data_hashMap.keySet())
			{
	            int key =name;
	            String value = data_hashMap.get(name).toString();  
	            memory = memory.concat("<"+key+","+value+">,");  
			}
			memory = memory.substring(1,memory.length()-1);
			
			//System.out.println("DAM: "+memory);
			wr.write("DAM: "+memory+"\n");
			//System.out.println();
			wr.write("\n");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		counter++;
		
		
	}
	
	
	//Decode method
	public static void decode()
	{
		if(!INM.isEmpty())
		{
			String instruction = INM.get(0);
			INM.remove(0);
			String[] ins_content = instruction.split(",");
			int l = ins_content.length;
			ins_content[0]= ins_content[0].substring(1);
			int last_len = ins_content[l-1].length();
			ins_content[l-1]= ins_content[l-1].substring(0, last_len-1);
			
			INB[0] = ins_content[0];
			INB[1] = ins_content[1];
			INB[2] = Integer.toString(reg_hashMap.get(ins_content[2]));
			INB[3] = Integer.toString(reg_hashMap.get(ins_content[3]));
		}
	}
	
	//ISSUE1 method
	public static void issue1()
	{
		if(INB[0]!=null)
		{
			for(int i=0;i<AIB.length;i++)
			{
				AIB[i]=INB[i];
			}
			Arrays.fill(INB, null);
		}
	}	
	
	
	//ISSUE2 method
	public static void issue2()
	{
		if(INB[0] != null)
		{
			for(int i=0;i<LIB.length;i++)
			{
				LIB[i]=INB[i];
			}
			Arrays.fill(INB, null);
		}
	}
	
	
	//ADDR method
	public static void addr()
	{
		if(LIB[0]!=null)
		{
		ADB[0] = LIB[1];
		ADB[1] = Integer.toString(Integer.parseInt(LIB[2]) + Integer.parseInt(LIB[3]));
		Arrays.fill(LIB, null);
		}
	}
	
	
	//ALU method
	public static void alu()
	{
		if(AIB[0] != null)
		{
			String result="";
			if(AIB[0].startsWith("ADD"))
			{
				result = Integer.toString(Integer.parseInt(AIB[2]) + Integer.parseInt(AIB[3]));
				String final_string = "<".concat(AIB[1]+","+result+">");
				REB.add(final_string);
				//Arrays.fill(AIB, null);
			}
			
			if(AIB[0].startsWith("SUB"))
			{
				result = Integer.toString(Integer.parseInt(AIB[2]) - Integer.parseInt(AIB[3]));
				String final_string = "<".concat(AIB[1]+","+result+">");
				REB.add(final_string);
				//Arrays.fill(AIB, null);
			}
			
			if(AIB[0].startsWith("AND"))
			{
				result = Integer.toString(Integer.parseInt(AIB[2]) & Integer.parseInt(AIB[3]));
				String final_string = "<".concat(AIB[1]+","+result+">");
				REB.add(final_string);
				//Arrays.fill(AIB, null);
			}
			
			if(AIB[0].startsWith("OR"))
			{
				result = Integer.toString(Integer.parseInt(AIB[2]) | Integer.parseInt(AIB[3]));
				String final_string = "<".concat(AIB[1]+","+result+">");
				REB.add(final_string);
				//Arrays.fill(AIB, null);
			}
			Arrays.fill(AIB, null);
		}
	}
	
	
	//LOAD method
	public static void load()
	{
		if(ADB[0] != null)
		{
			int val = data_hashMap.get(Integer.parseInt(ADB[1]));
			String res = "<".concat(ADB[0]+","+Integer.toString(val)+">");
			REB.add(res);
			Arrays.fill(ADB, null);
		}
	}

	
	//WRITE method
	public static void write()
	{
		if(!REB.isEmpty())
		{
			String data = REB.get(0);
			String key = data.substring(1, 3);
			int value = Integer.parseInt(data.substring(4,data.length()-1));
			reg_hashMap.put(key, value);
			REB.remove(0);
		}
	}
	
	public static void main(String[] args) 
	{
		String instructions = "instructions.txt";
		String dataMemory = "datamemory.txt";
		String register = "registers.txt";
		
		BufferedReader ins_br = null;
		FileReader ins_fr = null;
		BufferedReader data_br = null;
		FileReader data_fr = null;
		BufferedReader reg_br = null;
		FileReader reg_fr = null;
		
		ArrayList<String> instruct_list = new ArrayList<String>();
		try 
		{
			//for reading instructions.txt
			ins_fr = new FileReader(instructions);
			ins_br = new BufferedReader(ins_fr);
			
			//for reading data_memory.txt
			data_fr = new FileReader(dataMemory);
			data_br = new BufferedReader(data_fr);
			
			//for reading register.txt
			reg_fr = new FileReader(register);
			reg_br = new BufferedReader(reg_fr);
			
			String line_ins;
			String line_data;
			String line_reg;

			ins_br = new BufferedReader(new FileReader(instructions));
			data_br = new BufferedReader(new FileReader(dataMemory));
			reg_br = new BufferedReader(new FileReader(register));
			
			while ((line_data = data_br.readLine()) != null) 
			{
				int c = Character.getNumericValue(line_data.charAt(1));
				int len = line_data.length();
				String sub = line_data.substring(3,len-1);
				int val = Integer.parseInt(sub);
				data_hashMap.put(c, val);
			}
			
			while ((line_reg = reg_br.readLine()) != null) 
			{
				String reg_sub = line_reg.substring(1, 3);
				int len_reg = line_reg.length();
				String reg_val_str = line_reg.substring(4, len_reg-1);
				int reg_val = Integer.parseInt(reg_val_str);
				reg_hashMap.put(reg_sub, reg_val);
			}

			while ((line_ins = ins_br.readLine()) != null) 
			{
				instruct_list.add(line_ins);
			}
			
			try
	        {
	        	wr = new BufferedWriter(new FileWriter(new File("simulation.txt")));
	        }catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
			
			INM = instruct_list;	
			int size = INM.size();
			display();
			while(!INM.isEmpty() || INB[0]!=null || LIB[0]!=null || AIB[0]!=null || ADB[0]!=null || !REB.isEmpty())
			{	
				if(clear >size)
					Arrays.fill(INB, null);
				write();
				load();
				addr();
				alu();
				
				if(INB[0] !=null)
				{	
					//String[] test = INM.get(0).split(",");
					//test[0]= test[0].substring(1);
					String test = INB[0];
					//System.out.println("Opcode: "+test);
					if(test.startsWith("LD"))
						issue2();
					else
						issue1();
				}
				decode();
				display();
				clear++;
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (ins_br != null)
					ins_br.close();
				if (ins_fr != null)
					ins_fr.close();
				if (data_br != null)
					data_br.close();
				if (data_fr != null)
					data_fr.close();
				if (reg_br != null)
					reg_br.close();
				if (reg_fr != null)
					reg_fr.close();
				
				wr.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
}