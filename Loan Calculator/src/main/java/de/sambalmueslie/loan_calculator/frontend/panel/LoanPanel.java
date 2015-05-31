/**
 *
 */
package de.sambalmueslie.loan_calculator.frontend.panel;

import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL_BORDER;
import static de.sambalmueslie.loan_calculator.frontend.Constants.CLASS_PANEL_EMPTY;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import de.sambalmueslie.loan_calculator.backend.loan_mgt.Loan;
import de.sambalmueslie.loan_calculator.frontend.Constants;
import de.sambalmueslie.loan_calculator.frontend.component.*;
import de.sambalmueslie.loan_calculator.frontend.i18n.I18n;

/**
 * A abstract {@link Loan} panel.
 *
 * @author sambalmueslie 2015
 * @param <T>
 *            the {@link Loan} type
 */
public abstract class LoanPanel<T extends Loan> extends VBox {
	/**
	 * The handler for the {@link TextFieldChangeListener}.
	 *
	 * @author sambalmueslie 2015
	 */
	private class TextFieldChangeHandler implements TextFieldChangeListener {
		/**
		 * @see de.sambalmueslie.loan_calculator.frontend.component.TextFieldChangeListener#valueChanged()
		 */
		@Override
		public void valueChanged() {
			handleValueChanged();
		}
	}

	/**
	 * Constructor.
	 *
	 * @param loan
	 *            {@link #loan}
	 */
	@SuppressWarnings("unchecked")
	protected LoanPanel(final T loan) {
		this.loan = loan;
		this.loan.register(l -> update((T) l));

		getStyleClass().add(CLASS_PANEL);

		setupHeadline();

		final HBox content = new HBox();
		content.getStyleClass().add(CLASS_PANEL_EMPTY);

		infoPanel = new TilePane();
		infoPanel.getStyleClass().add(Constants.CLASS_PANEL_BORDER);
		infoPanel.setPrefColumns(2);
		content.getChildren().add(infoPanel);
		addInputInfo(I18n.get(I18n.TEXT_AMOUNT), loan.getAmount(), TextFieldType.CURRENCY);

		chartPane = new TilePane();
		chartPane.setPrefColumns(1);
		chartPane.setPrefWidth(600);
		chartPane.getStyleClass().add(CLASS_PANEL_BORDER);

		content.getChildren().add(chartPane);

		getChildren().add(content);
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 */
	protected void addChart(final Node chart) {
		chartPane.getChildren().add(chart);
	}

	/**
	 * Add a info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param type
	 *            the {@link TextFieldType}
	 */
	protected void addInfo(final String name, final Object value, final TextFieldType type) {
		addInfo(name, value, type, false);
	}

	/**
	 * Add a input info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param type
	 *            the {@link TextFieldType}
	 */
	protected void addInputInfo(final String name, final Object value, final TextFieldType type) {
		addInfo(name, value, type, true);
	}

	/**
	 * Get the info value.
	 *
	 * @param name
	 *            the name
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	protected <S> S getInfoValue(final String name) {
		final BaseTextField<S> textField = (BaseTextField<S>) textFields.get(name);
		return (textField == null) ? null : textField.getValue();
	}

	/**
	 * @return the {@link #loan}
	 */
	protected T getLoan() {
		return loan;
	}

	/**
	 * The value has changed.
	 */
	protected abstract void handleValueChanged();

	/**
	 * Update.
	 */
	protected abstract void update();

	/**
	 * Update a info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	@SuppressWarnings("unchecked")
	protected <S> void updateInfo(final String name, final S value) {
		final BaseTextField<S> textField = (BaseTextField<S>) textFields.get(name);
		if (textField == null) {
			return;
		}
		textField.setValue(value);
	}

	/**
	 * Add a info field.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param type
	 *            the {@link TextFieldType}
	 * @param input
	 *            input flag
	 */
	@SuppressWarnings("unchecked")
	private <S> void addInfo(final String name, final S value, final TextFieldType type, final boolean input) {
		final Label label = new Label(name);
		final BaseTextField<S> textField = (BaseTextField<S>) createTextField(type);
		textField.setValue(value);
		textField.setEditable(input);
		textFields.put(name, textField);
		infoPanel.getChildren().addAll(label, textField);
		if (input) {
			textField.setChangeListener(handler);
		}
	}

	/**
	 * Create a {@link TextField} for {@link TextFieldType}.
	 *
	 * @param type
	 *            the type
	 * @return the {@link TextField}
	 */
	private BaseTextField<?> createTextField(final TextFieldType type) {
		switch (type) {
		default:
		case TEXT:
			return new SimpleTextField();
		case CURRENCY:
			return new CurrencyTextField();
		case PERCENTAGE:
			return new PercentageTextField();
		case NUMBER:
			return new NumberTextField();
		case DATE:
			return new DateTextField();
		}
	}

	/**
	 * Setup the headline.
	 */
	private void setupHeadline() {
		final Label nameLabel = new Label(loan.getName());
		nameLabel.getStyleClass().add(CLASS_HEADLINE_LABEL);
		final HBox headline = new HBox(nameLabel);
		getChildren().add(headline);
	}

	/**
	 * Update.
	 *
	 * @param loan
	 *            the {@link Loan}.
	 */
	private void update(final T loan) {
		chartPane.getChildren().clear();
		updateInfo(I18n.get(I18n.TEXT_AMOUNT), loan.getAmount());
		update();
	}

	/** the chart pane}. */
	private final TilePane chartPane;
	/** the {@link TextFieldChangeHandler}. */
	private final TextFieldChangeHandler handler = new TextFieldChangeHandler();
	/** the info {@link TilePane}. */
	private final TilePane infoPanel;
	/** the loan. */
	private final T loan;
	/** the current {@link BaseTextField}s. */
	private final Map<String, BaseTextField<?>> textFields = new HashMap<>();

}
