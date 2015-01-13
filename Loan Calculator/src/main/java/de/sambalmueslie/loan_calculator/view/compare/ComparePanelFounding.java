/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.Set;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.chart.founding.AnnuityPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.founding.RedemptionPlanChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericBarChart;
import de.sambalmueslie.loan_calculator.view.chart.generic.GenericPieChart;

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
		final TilePane comparePane = new TilePane(Orientation.HORIZONTAL);
		comparePane.setPadding(new Insets(Constants.DEFAULT_SPACING));
		comparePane.setVgap(Constants.DEFAULT_SPACING);
		comparePane.setHgap(Constants.DEFAULT_SPACING);
		comparePane.setPrefColumns(4);

		comparePane.getChildren().add(addCompareFunction("Total payment", Founding::getTotalPayment));
		comparePane.getChildren().add(addCompareFunction("Total interest", Founding::getTotalInterest));
		comparePane.getChildren().add(addCompareFunction("Total Amount", Founding::getAmount));
		comparePane.getChildren().add(addCompareFunction("Term", Founding::getTerm));
		setTop(comparePane);

		final TilePane detailsPane = new TilePane();
		detailsPane.setPadding(new Insets(Constants.DEFAULT_SPACING));
		detailsPane.setVgap(Constants.DEFAULT_SPACING);
		detailsPane.setHgap(Constants.DEFAULT_SPACING);
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
		detailsPane.setVgap(Constants.DEFAULT_SPACING);
		detailsPane.setHgap(Constants.DEFAULT_SPACING);

		final Label title = new Label(founding.getName());
		title.getStyleClass().add("headline-label");
		title.setAlignment(Pos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		detailsPane.add(title, 0, 0, 2, 1);

		detailsPane.add(new RedemptionPlanChart(founding), 0, 1);
		detailsPane.add(new AnnuityPlanChart(founding), 0, 2);

		final GenericPieChart<Founding, Loan> totalAmountChart = new GenericPieChart<>(Founding::getLoans, Loan::getAmount, "Total amount", founding);
		detailsPane.add(totalAmountChart, 0, 3);

		final GenericPieChart<Founding, Loan> totalPaymentChart = new GenericPieChart<>(Founding::getLoans, Loan::getTotalPayment, "Total payment", founding);
		detailsPane.add(totalPaymentChart, 0, 4);

		final GenericPieChart<Founding, Loan> totalInterestChart = new GenericPieChart<>(Founding::getLoans, Loan::getTotalInterest, "Total interest", founding);
		detailsPane.add(totalInterestChart, 0, 5);

		return detailsPane;
	}

}
