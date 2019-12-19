package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class DateChooser extends HBox implements Initializable {

	// MODEL

	private ObjectProperty<LocalDate> day = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> month = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> year = new SimpleObjectProperty<LocalDate>();

	// VIEW

	@FXML
	private HBox view;

	@FXML
	private ComboBox<String> dayComboBox, monthComboBox, yearComboBox;

	public DateChooser() throws IOException {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateChooserView.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// YEARS

		ListProperty<String> yearsList = new SimpleListProperty<String>(FXCollections.observableArrayList());

		int startingYear = 1900;
		int currentYear = Year.now().getValue();

		for (int i = currentYear; i > startingYear - 1; i--) {
			yearsList.add(Integer.toString(i));
		}

		yearComboBox.itemsProperty().bind(yearsList);
		
		year.bind(yearComboBox.getSelectionModel().getSelectedItem());

		// MONTHS

		ListProperty<String> monthsList = new SimpleListProperty<String>(FXCollections.observableArrayList());

		String[] months = new DateFormatSymbols().getMonths();
		
		for (int i = 0; i < months.length - 1; i++) {
			monthsList.add(months[i]);
		}
		
		monthComboBox.itemsProperty().bind(monthsList);
		
		// DAYS
		
		ListProperty<String> daysList = new SimpleListProperty<String>(FXCollections.observableArrayList());
		
		int selectedYear = 2019;
		int selectedMonth = 2;
		
		int numOfDays = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();
		
		for (int i = 0; i < numOfDays; i++) {
			daysList.add(Integer.toString(i + 1));
		}
		
		dayComboBox.disableProperty().bind(
				monthComboBox.getSelectionModel().selectedItemProperty().isNull()
				);
		
		dayComboBox.itemsProperty().bind(daysList);

	}

	// FX GETTERS AND SETTERS

	public final ObjectProperty<LocalDate> dayProperty() {
		return this.day;
	}

	public final LocalDate getDay() {
		return this.dayProperty().get();
	}

	public final void setDay(final LocalDate day) {
		this.dayProperty().set(day);
	}

	public final ObjectProperty<LocalDate> monthProperty() {
		return this.month;
	}

	public final LocalDate getMonth() {
		return this.monthProperty().get();
	}

	public final void setMonth(final LocalDate month) {
		this.monthProperty().set(month);
	}

	public final ObjectProperty<LocalDate> yearProperty() {
		return this.year;
	}

	public final LocalDate getYear() {
		return this.yearProperty().get();
	}

	public final void setYear(final LocalDate year) {
		this.yearProperty().set(year);
	}

}
