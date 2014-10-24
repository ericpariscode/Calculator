package com.done.projectclass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity{
	
	private EditText editText;
	private int lastOpt=-1;
	private double lastDoubleNum = Double.MAX_VALUE;
	private boolean isSpecial=false;
	private boolean isNegative=false;
	private double memoryNumber=0;
	private long lastNum=Long.MAX_VALUE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);
		editText=(EditText)findViewById(R.id.result);
		
		Button btn_add=(Button)findViewById(R.id.btn_add);
		
		btn_add.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	lastOpt = 1;
		    	HandleLastValue();
		    }
		});
		
		Button btn_div=(Button)findViewById(R.id.btn_div);
		
		btn_div.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	lastOpt=4;
		    	HandleLastValue();
		    }
		});
		
		Button btn_sub=(Button)findViewById(R.id.btn_sub);
		
		btn_sub.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	lastOpt = 2;
		    	HandleLastValue();
		    }
		});
		
		Button btn_mul=(Button)findViewById(R.id.btn_mul);
		
		btn_mul.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	lastOpt = 3;
		    	HandleLastValue();
		    }
		});
		
		Button btn_equal=(Button)findViewById(R.id.btn_equal);
		
		btn_equal.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if (lastOpt != -1) {
						if(isSpecial==false){
							long result = calLong();
							editText.setText(String.valueOf(result));
							lastOpt = -1;
							lastNum = Long.MAX_VALUE;
						}
						else{
							double result=roundDouble(calDouble());
							editText.setText(String.valueOf(result));
							lastOpt=-1;
							lastDoubleNum=Double.MAX_VALUE;	
						}
					}
		    	}
		    	catch(Exception e){
		    		
		    	}
		    }
		});
		
		
		Button btn_negative=(Button)findViewById(R.id.btn_negative);
		
		btn_negative.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(isSpecial==false){
			    		if(!editText.getText().toString().equals("")){
			    			if(isNegative==true){
				    			long num=Long.valueOf(editText.getText().toString());
				    			num=-num;
					    		editText.setText(String.valueOf(num));
					    		isNegative=false;
					    	}
					    	else{
					    		long num=Long.valueOf(editText.getText().toString());
					    		num=-num;
					    		editText.setText(String.valueOf(num));
					    		isNegative=true;
					    	}
			    		}
			    	}
			    	else{
			    		if(!editText.getText().toString().equals("")){
			    			if(isNegative==true){
				    			double num=Double.valueOf(editText.getText().toString());
				    			num=-num;
					    		editText.setText(String.valueOf(num));
					    		isNegative=false;
					    	}
					    	else{
					    		double num=Double.valueOf(editText.getText().toString());
					    		num=-num;
					    		editText.setText(String.valueOf(num));
					    		isNegative=true;
					    	}
			    		}
			    		
			    	}
		    	}
		    	catch(Exception e){
		    		
		    	}
		    }
		});
		
		Button btn_mc=(Button)findViewById(R.id.btn_mc);
		
		btn_mc.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	memoryNumber=0;	
		    }
		});
		
		Button btn_mr=(Button)findViewById(R.id.btn_mr);
		
		btn_mr.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	if(isSpecial==true){
		    		lastDoubleNum=memoryNumber;
		    		editText.setText(String.valueOf("MR"));
		    		if(lastOpt==-1){
		    			lastOpt=5;
		    		}
		    	}
		    	else{
		    		lastNum=(long)memoryNumber;
		    		editText.setText(String.valueOf("MR"));
		    		if(lastOpt==-1){
		    			lastOpt=5;
		    		}
		    	}
		    }
		});
		
		Button btn_ms=(Button)findViewById(R.id.btn_ms);
		
		btn_ms.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	if(!editText.getText().toString().equals("")){
		    		memoryNumber=Double.valueOf(editText.getText().toString());
		    	}
		    }
		});
		
		Button btn_mplus=(Button)findViewById(R.id.btn_mplus);
		
		btn_mplus.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	if(!editText.getText().toString().equals("")){
		    		memoryNumber=memoryNumber+Double.valueOf(editText.getText().toString());
		    	}
		    }
		});
		
		Button btn_clean=(Button)findViewById(R.id.btn_clean);
		
		btn_clean.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
	    		lastOpt = -1;
				editText.setText("");
				isSpecial=false;	
		    }
		});
		
		Button btn_dot=(Button)findViewById(R.id.btn_dot);
		
		btn_dot.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	Button btn= (Button)v;
				btn.getText();
				editText.setText(editText.getText().toString()+btn.getText());
				isSpecial=true;
		    }
		});
		
		Button btn_sqrt=(Button)findViewById(R.id.btn_sqrt);
		
		btn_sqrt.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(!editText.getText().toString().equals("")){
			    		double result=0;
			    		try{
			    			 result=Math.sqrt(Double.valueOf(editText.getText().toString()));
			    		}
			    		catch(Exception e){
			    			
			    		}
						editText.setText(String.valueOf(result));
			    	}  
		    	}
		    	catch(Exception e){
		    		
		    	}
		    }
		});
		
		
		Button btn_sin=(Button)findViewById(R.id.btn_sin);
		
		btn_sin.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(!editText.getText().toString().equals("")){
			    		double result=Math.sin(Double.valueOf(editText.getText().toString()));
						editText.setText(String.valueOf(result));
			    	}
		    	}
		    	catch(Exception e){
		    		
		    	}
			  
		    }
		});

		Button btn_inverse=(Button)findViewById(R.id.btn_inverse);
		
		btn_inverse.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(!editText.getText().toString().equals("")){
			    		double result=1.0/Double.valueOf(editText.getText().toString());
						editText.setText(String.valueOf(result));
			    	}
		    	}
		    	catch(Exception e){
		    		
		    	}
		    }
		});
		
		Button btn_squared=(Button)findViewById(R.id.btn_square);
		
		btn_squared.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	lastOpt = 6;
		    	HandleLastValue();
		    }
		});
		
		Button btn_tan=(Button)findViewById(R.id.btn_tan);
		
		btn_tan.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(!editText.getText().toString().equals("")){
			    		double result=Math.tan(Double.valueOf(editText.getText().toString()));
						editText.setText(String.valueOf(result));
			    	}
		    	}
		    	catch(Exception e){
		    		
		    	}
			  
		    }
		});
		
		Button btn_cos=(Button)findViewById(R.id.btn_cos);
		
		btn_cos.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	try{
		    		if(!editText.getText().toString().equals("")){
			    		double result=Math.cos(Double.valueOf(editText.getText().toString()));
						editText.setText(String.valueOf(result));
			    	}
		    	}
		    	catch(Exception e){
		    		
		    	}
		    }
		});	
	}
	
	public void HandleLastValue(){
		try{
    		if(isSpecial==true){
	    		if(!editText.getText().toString().equals("")){
		    		if(editText.getText().toString().equals("MR")){
		    			lastDoubleNum=memoryNumber;
		    		}
		    		else{
		    			lastDoubleNum = Double.valueOf(editText.getText().toString());
		    		}
					editText.setText("");
	    		}
	    	}
	    	else{
	    		if(!editText.getText().toString().equals("")){
		    		if(editText.getText().toString().equals("MR")){
		    			lastNum=(long)memoryNumber;
		    		}
		    		else{
		    			lastNum = Long.valueOf(editText.getText().toString());
		    		}
					editText.setText("");
	    		}
	    	}
    	}
    	catch(Exception e){
    		
    	}
	}

	public double Squared(double num,long k){
		double result=1;
		for(int i=0;i<k;i++){
			result=num*result;
		}
		return result;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}
	
	public void numClick(View view) {
		Button btn = (Button) view;
		btn.getText();
		editText.setText(editText.getText().toString() + btn.getText());
	}
	
	public long calLong() {
		switch (lastOpt) {
		case 1:
			if(editText.getText().toString().equals("MR")){
				return lastNum+(long)memoryNumber;
			}
			return lastNum + Long.valueOf(editText.getText().toString());
		case 2:
			if(editText.getText().toString().equals("MR")){
				return lastNum-(long)memoryNumber;
			}
			return lastNum - Long.valueOf(editText.getText().toString());
		case 3:
			if(editText.getText().toString().equals("MR")){
				return lastNum*(long)memoryNumber;
			}
			return lastNum * Long.valueOf(editText.getText().toString());
		case 4:
			if(editText.getText().toString().equals("MR")){
				return lastNum/(long)memoryNumber;
			}
			return lastNum / Long.valueOf(editText.getText().toString());
		case 5:
			return (int)memoryNumber;
		case 6:
			if(editText.getText().toString().equals("MR")){
				return SquaredLong(lastNum,(long)memoryNumber);
			}
			return SquaredLong(lastNum,Long.valueOf(editText.getText().toString()));
		
		}
		return Long.MAX_VALUE;
	}
	
	public long SquaredLong(long num,long k){
		long result=1;
		for(long i=0;i<k;i++){
			result*=num;
		}
		return result;
	}
	
	public double roundDouble(double value){
		double rounded = (double) Math.round(value * 100) / 100;
		return rounded;
	}
	
	public double calDouble() {
		switch (lastOpt) {
		case 1:
			if(editText.getText().toString().equals("MR")){
				return lastDoubleNum+memoryNumber;
			}
			return lastDoubleNum + Double.valueOf(editText.getText().toString());
		case 2:
			if(editText.getText().toString().equals("MR")){
				return lastDoubleNum-memoryNumber;
			}
			return lastDoubleNum - Double.valueOf(editText.getText().toString());
		case 3:
			if(editText.getText().toString().equals("MR")){
				return lastDoubleNum*memoryNumber;
			}
			return lastDoubleNum * Double.valueOf(editText.getText().toString());
		case 4:
			if(editText.getText().toString().equals("MR")){
				return lastDoubleNum/memoryNumber;
			}
			return lastDoubleNum / Double.valueOf(editText.getText().toString());
		
		case 5:
			return memoryNumber;
		case 6:
			if(editText.getText().toString().equals("MR")){
				return Squared(lastDoubleNum,(int)memoryNumber);
			}
			return Squared(lastDoubleNum,Long.valueOf(editText.getText().toString()));
		}
		return Double.MAX_VALUE;
	}
}
