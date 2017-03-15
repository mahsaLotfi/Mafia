package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class NightPanel{
	
	
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	private Color selectColor;
	
	private Font titleFont;
	private Font btnFont;
	private Font infoFont;

	private JPanel contentPane;
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	private JLabel lblName;
	private JLabel lblRole;
	private JTextArea lblInfo;
	private JLabel lblMafia;
	private JLabel lblDetective;
	
	private JButton btnContinue;
	private JButton btnDetective;
	
	//Current position in the list of players
	private int target = -1;
	
	private List<Player> playerInfo;
	
	private List<JButton> buttonList = new ArrayList<>();

	/**
	 * Create the panel and all of the sub panels
	 * Displays all of the needed buttons and labels etc...
	 * @param playerInfo
	 */
	public NightPanel(List<Player> playerInfo) {
		this.playerInfo = playerInfo;
		
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[][]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));

		displaySouth();
		//Initializes the labels, does not fill them with anything
		displayNorth();
		displayCenter();
		setBackground(backgroundColor);
	}
	
	
	/**
	 * Display the name, role, info, and Mafia members (if applicable)
	 */
	private void displayNorth(){
		String text = "";
		lblName = new MyLabel(text, textColor, titleFont);
		north.add(lblName, "flowy,cell 0 0");
		lblRole = new MyLabel(text, textColor, btnFont);
		north.add(lblRole, "cell 0 1");
		lblInfo = new MyTextArea(text, textColor, backgroundColor, infoFont);
		north.add(lblInfo, "cell 0 2");
		lblMafia= new MyLabel(text, textColor, infoFont);
		north.add(lblMafia, "cell 0 3");
	}
	
	/**
	 * This displays all of the possible buttons that each player can press when it is his/ her turn
	 * Each button represents a player
	 */
	private void displayCenter(){
		int k=0;
		for(int i=0;i<playerInfo.size();i++){
			if(!playerInfo.get(i).isDead()){
				displayPlayerButton(i);
			}
			k = i+1;
		}
		
		btnDetective = new MyButton("Confirm Target", textColor, btnBackgroundColor, btnFont);
		center.add(btnDetective, "cell 0 "+k+",alignx center");
		btnDetective.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if(target!=-1){
    				checkMafia();
    				btnDetective.setVisible(false);
    			}
		}});
		btnDetective.setVisible(false);
		lblDetective = new MyLabel("", textColor, btnFont);
		center.add(lblDetective, "cell 0 "+k+1+",alignx center");
		
	}
	
	private void displayEast(){
		
	}
	
	private void displayWest(){
		
	}
	/**
	 * Displays the button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnContinue = new MyButton("Continue", textColor, btnBackgroundColor, btnFont);
		south.add(btnContinue, "cell 0 0");
		btnContinue.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().rotateNightPlayer(target);
		}});
	}
	/**
	 * Creates a button with the text value of a player depending on i
	 * @param i
	 */
	private void displayPlayerButton(int i){
		String text = playerInfo.get(i).getName();
		JButton btnPlayer = new MyButton(text,textColor,btnBackgroundColor,btnFont);
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position);
		btnPlayer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			for(int m=0;m<buttonList.size();m++){
    				buttonList.get(m).setBackground(btnBackgroundColor);
    			}
    			btnPlayer.setBackground(selectColor);
    			target = i;
    			
		}});
		buttonList.add(btnPlayer);
	}
	
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	private void setFont(){
		titleFont = new MyFont(100);
		btnFont = new MyFont(50);
		infoFont = new MyFont(20);
	}
	
	private void setColor(){
		selectColor = Colors.blue;
		textColor = Colors.black;
		btnBackgroundColor = Colors.white;
		backgroundColor = Colors.grey;
	}
	
	private void checkMafia(){
		if(playerInfo.get(target).isMafia()){
			lblDetective.setText("Part of the Mafia");
		}else{
			lblDetective.setText("Not Part of the Mafia");
		}
	}
	
	public void setPlayerInfo(List<Player> pI){
		playerInfo = pI;
	}
	
	public Integer getPlayerTarget(){
		return target;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	/**
	 * Sets the display for the new player at night
	 * @param i, position in list of current player
	 * @param mafiaMember
	 */
	public void setDisplay(int i, List<String> mafiaMember){
		target = -1;
		lblDetective.setText("");
		lblMafia.setText("");
		btnDetective.setVisible(false);
		lblName.setText(playerInfo.get(i).getName());
		lblRole.setText(playerInfo.get(i).getRole());
		lblInfo.setText(playerInfo.get(i).getRoleInfo());
		for(int m=0;m<buttonList.size();m++){
			buttonList.get(m).setBackground(btnBackgroundColor);
		}
		if(playerInfo.get(i).getRole().contains("Detective")){
			btnDetective.setVisible(true);
		}
		if(playerInfo.get(i).getRole().contains("Mafia")){
			lblMafia.setText("Mafia Members: "+ mafiaMember);
		}
	}
}