package ControllPanel;

import com.smarthome.publisher.LightControlImpl;
import com.smarthome.publisher.SmartLightingControl;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class SmartLightUI {

    private final SmartLightingControl smartLightingControl;
    private final JFrame frame;
    private final JPanel roomsPanel;
    private final Map<String, Boolean> roomStatus;
    private final Map<String, Integer> roomBrightness;
    private final JTextArea logArea;


    //Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartLightUI ui = new SmartLightUI();
            ui.display();
        });
    }

    //Constructor
    public SmartLightUI() {
        
        smartLightingControl = new LightControlImpl();
        
        
        roomStatus = new HashMap<>();
        roomBrightness = new HashMap<>();
        
       
        initializeDefaultRooms();
        
        frame = new JFrame("Smart Lighting Control System");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        
        JPanel headerPanel = createHeaderPanel();
        frame.add(headerPanel, BorderLayout.NORTH);
        
        roomsPanel = new JPanel();
        roomsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        roomsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(roomsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        logArea = new JTextArea(8, 40);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("System Log"));
        frame.add(logScrollPane, BorderLayout.SOUTH);
    }

    //Default Rooms
    private void initializeDefaultRooms() {
        // Add default rooms
        String[] defaultRooms = {"Living Room", "Kitchen", "Master Bedroom", "Guest Room"};
        
        for (String room : defaultRooms) {
            roomStatus.put(room, false); 
            roomBrightness.put(room, 50); 
        }
    }

    //Panel Header
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Smart Home Lighting Control");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Add room button
        JButton addRoomButton = new JButton("Add New Room + ");
        addRoomButton.setFont(new Font("Arial", Font.BOLD, 12));
        addRoomButton.setBackground(new Color(100, 200, 100));
        addRoomButton.setForeground(Color.BLACK);
        addRoomButton.addActionListener(e -> showAddRoomDialog());
        headerPanel.add(addRoomButton, BorderLayout.EAST);
        
        return headerPanel;
    }

    //Main display section
    public void display() {
        loadRoomsUI();
       
        frame.setLocationRelativeTo(null);
      
        frame.setVisible(true);
       
        logMessage("Smart Lighting Control System started successfully");
    }

    //All rooms display
    private void loadRoomsUI() {
       
        roomsPanel.removeAll();
       
        for (String roomName : roomStatus.keySet()) {
            JPanel roomCard = createRoomCard(roomName);
            roomsPanel.add(roomCard);
        }
       
        roomsPanel.revalidate();
        roomsPanel.repaint();
    }

    //Card component
    private JPanel createRoomCard(String roomName) {
       
        boolean isOn = roomStatus.get(roomName);
        int brightness = roomBrightness.get(roomName);
        
        JPanel roomCard = new JPanel();
        roomCard.setLayout(new BorderLayout(5, 5));
        roomCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
       
        Color cardColor = isOn ? 
            new Color(255, 255, 200) : 
            new Color(240, 240, 240); 
        roomCard.setBackground(cardColor);
        
     
        JLabel nameLabel = new JLabel(roomName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomCard.add(nameLabel, BorderLayout.NORTH);
       
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
        controlsPanel.setOpaque(false);
       
        JLabel statusLabel = new JLabel("Status: " + (isOn ? "ON" : "OFF"));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        controlsPanel.add(statusLabel);
        controlsPanel.add(Box.createVerticalStrut(5));
        
        JLabel brightnessLabel = new JLabel("Brightness: " + brightness + "%");
        brightnessLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        controlsPanel.add(brightnessLabel);
        controlsPanel.add(Box.createVerticalStrut(10));
      
        JButton controlButton = new JButton("Control Room");
        controlButton.setBackground(new Color(70, 130, 180));
        controlButton.setForeground(Color.BLACK);
        controlButton.addActionListener(e -> openRoomControlDialog(roomName));
        controlsPanel.add(controlButton);
        
        roomCard.add(controlsPanel, BorderLayout.CENTER);
       
        //Bulb indicator
        JPanel indicatorPanel = new JPanel() {
          
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isOn) {
                   
                    int brightnessFactor = (int) (255 * (brightness / 100.0));
                    Color lightColor = new Color(brightnessFactor, brightnessFactor, 0);  // Adjust yellow based on brightness
                    g2d.setColor(lightColor);
                    g2d.fillOval(5, 5, 30, 30);
                    g2d.setColor(Color.ORANGE);
                    g2d.drawOval(5, 5, 30, 30);
                } else {
                   
                    g2d.setColor(new Color(200, 200, 200));
                    g2d.fillOval(5, 5, 30, 30);
                    g2d.setColor(Color.GRAY);
                    g2d.drawOval(5, 5, 30, 30);
                }
            }

            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(40, 40);
            }
        };
        indicatorPanel.setOpaque(false);
        roomCard.add(indicatorPanel, BorderLayout.EAST);
        
        return roomCard;
    }

  
    
    
    
    //Create Room Dialog Box
    private void showAddRoomDialog() {
        JTextField roomNameField = new JTextField(20);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Enter room name:"));
        panel.add(roomNameField);
        
        int result = JOptionPane.showConfirmDialog(
            frame, panel, "Add New Room", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String roomName = roomNameField.getText().trim();
            if (!roomName.isEmpty()) {
                if (roomStatus.containsKey(roomName)) {
                    JOptionPane.showMessageDialog(
                        frame, 
                        "Room '" + roomName + "' already exists!", 
                        "Duplicate Room", 
                        JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    addNewRoom(roomName);
                }
            }
        }
    }

  
    //New room Adding
    private void addNewRoom(String roomName) {
        // Add to data structures
        roomStatus.put(roomName, false); 
        roomBrightness.put(roomName, 50); 
        
      
        loadRoomsUI();
        
       
        logMessage("New room added: " + roomName);
    }

   
    
    
    
    //Room Dialog Box
    private void openRoomControlDialog(String roomName) {
        try {
           
            boolean isOn = roomStatus.get(roomName);
            int brightness = roomBrightness.get(roomName);
            
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
            controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
           
            JLabel titleLabel = new JLabel(roomName);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            controlPanel.add(titleLabel);
            controlPanel.add(Box.createVerticalStrut(15));
           
            
            //On/Off option
            JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JToggleButton lightSwitch = new JToggleButton("Light", isOn);
            lightSwitch.setPreferredSize(new Dimension(100, 40));
            lightSwitch.setSelected(isOn);
            lightSwitch.setBackground(isOn ? Color.YELLOW : Color.LIGHT_GRAY);
            lightSwitch.setFont(new Font("Arial", Font.BOLD, 14));
          
            
            JLabel statusLabel = new JLabel(isOn ? "ON" : "OFF");
            statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
            statusLabel.setForeground(isOn ? new Color(0, 150, 0) : Color.GRAY);
            
            lightSwitch.addActionListener(e -> {
                boolean newState = lightSwitch.isSelected();
                if (newState) {
                    smartLightingControl.turnOnLight(roomName);
                    statusLabel.setText("ON");
                    statusLabel.setForeground(new Color(0, 150, 0));
                    lightSwitch.setBackground(Color.YELLOW);
                } else {
                    smartLightingControl.turnOffLight(roomName);
                    statusLabel.setText("OFF");
                    statusLabel.setForeground(Color.GRAY);
                    lightSwitch.setBackground(Color.LIGHT_GRAY);
                }
                roomStatus.put(roomName, newState);
                logMessage(roomName + " light turned " + (newState ? "ON" : "OFF"));
                
 
                SwingUtilities.invokeLater(() -> loadRoomsUI());
            });
            
            switchPanel.add(lightSwitch);
            switchPanel.add(Box.createHorizontalStrut(20));
            switchPanel.add(statusLabel);
            controlPanel.add(switchPanel);
            controlPanel.add(Box.createVerticalStrut(20));
            
            
            
            // Brightness control
            JPanel brightnessPanel = new JPanel(new BorderLayout(5, 5));
            brightnessPanel.setBorder(BorderFactory.createTitledBorder("Brightness"));
            
            JSlider brightnessSlider = new JSlider(0, 100, brightness);
            brightnessSlider.setMajorTickSpacing(20);
            brightnessSlider.setMinorTickSpacing(5);
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setPaintLabels(true);
            brightnessSlider.setFont(new Font("Arial", Font.PLAIN, 10));
            
            JLabel brightnessValueLabel = new JLabel(brightness + "%");
            brightnessValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
            brightnessValueLabel.setHorizontalAlignment(JLabel.CENTER);
            
            brightnessSlider.addChangeListener(e -> {
                int newBrightness = brightnessSlider.getValue();
                brightnessValueLabel.setText(newBrightness + "%");
                
           
                if (!brightnessSlider.getValueIsAdjusting()) {
                    roomBrightness.put(roomName, newBrightness);
                    smartLightingControl.adjustBrightness(roomName, newBrightness);
                    logMessage(roomName + " brightness set to " + newBrightness + "%");
                    
                  
                    SwingUtilities.invokeLater(() -> loadRoomsUI());
                }
            });
            
            brightnessPanel.add(brightnessSlider, BorderLayout.CENTER);
            brightnessPanel.add(brightnessValueLabel, BorderLayout.SOUTH);
            
            controlPanel.add(brightnessPanel);
            controlPanel.add(Box.createVerticalStrut(20));
            
           
            final JDialog dialog = new JDialog(frame, roomName + " - Light Control", true);
            
           
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            
            
            
            // Apply button
            JButton applyButton = new JButton("Apply Changes");
            applyButton.setFont(new Font("Arial", Font.BOLD, 12));
            applyButton.setBackground(new Color(100, 180, 100));
            applyButton.setForeground(Color.BLACK);
            applyButton.addActionListener(e -> {
                
                dialog.dispose();
                
                loadRoomsUI();
            });
            
            // Delete room button
            JButton deleteButton = new JButton("Delete Room");
            deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
            deleteButton.setBackground(new Color(200, 80, 80));
            deleteButton.setForeground(Color.BLACK);
            deleteButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(
                    dialog, 
                    "Are you sure you want to delete " + roomName + "?",
                    "Delete Room",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        
                        roomStatus.remove(roomName);
                        roomBrightness.remove(roomName);
                        
                       
                        dialog.dispose();
                        
                       
                        SwingUtilities.invokeLater(() -> {
                            loadRoomsUI();
                            logMessage("Room deleted: " + roomName);
                        });
                    } catch (Exception ex) {
                        logMessage("Error deleting room: " + ex.getMessage());
                        JOptionPane.showMessageDialog(
                            frame,
                            "Error deleting room: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });
            
            buttonPanel.add(applyButton);
            buttonPanel.add(deleteButton);
            
            controlPanel.add(buttonPanel);
            
           
            dialog.setContentPane(controlPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(frame);
            dialog.setResizable(false);
            dialog.setVisible(true);
            
        } catch (Exception ex) {
           
            logMessage("Error opening control dialog: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                frame,
                "Error opening control for room " + roomName + ": " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

   
    private void logMessage(String message) {
        try {
            String timestamp = java.time.LocalTime.now().toString().substring(0, 8);
            logArea.append("[" + timestamp + "] " + message + "\n");
           
            logArea.setCaretPosition(logArea.getDocument().getLength());
        } catch (Exception ex) {
           
            System.err.println("Error logging message: " + ex.getMessage());
        }
    }
}