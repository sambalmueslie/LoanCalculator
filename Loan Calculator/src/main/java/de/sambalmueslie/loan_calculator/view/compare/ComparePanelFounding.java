/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_HEADLINE_LABEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL;
import static de.sambalmueslie.loan_calculator.view.Constants.CLASS_PANEL_BORDER;

import java.util.Set;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.model.loan.RedemptionPlanEntry;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.chart.SeriesDefinition;
import de.sambalmueslie.loan_calculator.view.chart.founding.AnnuityPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.founding.RedemptionPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericBarChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericPieChart;
import de.sambalmueslie.loan_calculator.view.chart.loan.GenericAnnuityChart;
import de.sambalmueslie.loan_calculator.view.i18n.I18nPropertiesHandler;

/**
 * The compare panel for {@link Loan}s.
 *
 * @author sambalmueslie 2015
 */
class ComparePanelFounding extends BaseComparePanel<Founding> {

	/**
	 * Constructor.
	 *
	 * @param comparison
	 *            the {@link Comparison}
	 * @param actionListener
	 *            the {@link ViewActionListener}
	 * @param model
	 *            the {@link Model}
	 */
	ComparePanelFounding(final Comparison<Founding> comparison, final ViewActionListener actionListener, final Model model) {
		super(comparison, actionListener, model);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#get(long)
	 */
	@Override
	protected Founding get(final long entryId) {
		return getModel().getFounding(entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#getContextMenu()
	 */
	@Override
	protected ContextMenu getContextMenu() {
		return new FoundingCompareContextMenu(getActionListener(), getComparison());
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#requestComparisonAddEntry(long)
	 */
	@Override
	protected void requestComparisonAddEntry(final long entryId) {
		getActionListener().requestComparisonAddFounding(getComparison().getId(), entryId);
	}

	/**
	 * @see de.sambalmueslie.loan_calculator.view.compare.BaseComparePanel#setup(java.util.Set)
	 */
	@Override
	protected void setup(final Set<Founding> elements) {
		final TilePane comparePane = new TilePane();
		comparePane.getStyleClass().add(CLASS_PANEL);

		comparePane.getChildren().add(addCompareFunction("Total payment", Founding::getTotalPayment));
		comparePane.getChildren().add(
				addCompareFunction(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TOTAL_INTEREST), Founding::getTotalInterest));
		comparePane.getChildren().add(addCompareFunction(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TOTAL_AMOUNT), Founding::getAmount));
		comparePane.getChildren().add(addCompareFunction(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TERM), Founding::getTerm));
		comparePane.getChildren().add(addCompareFunction(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_RISK_CAPITAL), Founding::getRiskCapital));

		final Function<Founding, RedemptionPlanEntry> dataGetterFunction = (f -> f.getRedemptionPlan().get(1));
		final SeriesDefinition<RedemptionPlanEntry, Double> sd1 = new SeriesDefinition<>(I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_INTEREST),
				RedemptionPlanEntry::getInterest);
		final SeriesDefinition<RedemptionPlanEntry, Double> sd2 = new SeriesDefinition<>(
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_REDEMPTION), RedemptionPlanEntry::getRedemption);
		final GenericAnnuityChart<Founding, RedemptionPlanEntry, Double> chart = new GenericAnnuityChart<>("Annuitity", dataGetterFunction, elements, sd1, sd2);
		comparePane.getChildren().add(chart);

		setTop(comparePane);

		final TilePane detailsPane = new TilePane();
		detailsPane.getStyleClass().add(CLASS_PANEL);
		detailsPane.setPrefRows(1);

		elements.forEach(f -> detailsPane.getChildren().add(createDetailsPanel(f)));
		setCenter(detailsPane);
	}

	/**
	 * Add a compare function.
	 *
	 * @param title
	 *            the title
	 * @param function
	 *            the function
	 * @return the chart {@link Node}
	 */
	private Node addCompareFunction(final String title, final Function<Founding, Number> function) {
		final GenericBarChart<Founding> chart = new GenericBarChart<>(function, title);
		getComparison().getElements().forEach(f -> chart.add(f));
		return chart;
	}

	/**
	 * Create the details panel for a {@link Founding}.
	 *
	 * @param founding
	 *            the founding
	 * @return the {@link Node}
	 */
	private Node createDetailsPanel(final Founding founding) {
		final GridPane detailsPane = new GridPane();
		detailsPane.getStyleClass().add(CLASS_PANEL_BORDER);

		final Label title = new Label(founding.getName());
		title.getStyleClass().add(CLASS_HEADLINE_LABEL);
		title.setAlignment(Pos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		detailsPane.add(title, 0, 0, 2, 1);

		detailsPane.add(new RedemptionPlanChart(founding), 0, 1);
		detailsPane.add(new AnnuityPlanChart(founding), 0, 2);

		final GenericPieChart<Founding, Loan> totalAmountChart = new GenericPieChart<>(Founding::getLoans, Loan::getAmount,
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TOTAL_AMOUNT), founding);
		detailsPane.add(totalAmountChart, 0, 3);

		final GenericPieChart<Founding, Loan> totalPaymentChart = new GenericPieChart<>(Founding::getLoans, Loan::getTotalPayment,
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TOTAL_PAYMENT), founding);
		detailsPane.add(totalPaymentChart, 0, 4);

		final GenericPieChart<Founding, Loan> totalInterestChart = new GenericPieChart<>(Founding::getLoans, Loan::getTotalInterest,
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_TOTAL_INTEREST), founding);
		detailsPane.add(totalInterestChart, 0, 5);

		final GenericPieChart<Founding, Loan> riskCapitalChart = new GenericPieChart<>(Founding::getLoans, Loan::getRiskCapital,
				I18nPropertiesHandler.getString(I18nPropertiesHandler.TEXT_RISK_CAPITAL), founding);
		detailsPane.add(riskCapitalChart, 0, 6);

		return detailsPane;
	}
}
