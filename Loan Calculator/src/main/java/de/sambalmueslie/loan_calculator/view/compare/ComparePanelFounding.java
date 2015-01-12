/**
 *
 */
package de.sambalmueslie.loan_calculator.view.compare;

import java.util.Set;

import javafx.scene.layout.TilePane;
import de.sambalmueslie.loan_calculator.model.Model;
import de.sambalmueslie.loan_calculator.model.compare.Comparison;
import de.sambalmueslie.loan_calculator.model.founding.Founding;
import de.sambalmueslie.loan_calculator.model.loan.Loan;
import de.sambalmueslie.loan_calculator.view.Constants;
import de.sambalmueslie.loan_calculator.view.ViewActionListener;
import de.sambalmueslie.loan_calculator.view.chart.Chart;
import de.sambalmueslie.loan_calculator.view.chart.founding.FoundingChartFactory;

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
		chartPane = new TilePane();
		chartPane.setVgap(Constants.DEFAULT_SPACING);
		chartPane.setHgap(Constants.DEFAULT_SPACING);
		chartPane.setPrefColumns(2);

		addChart(FoundingChartFactory.createRedemptionPlanChart());
		addChart(FoundingChartFactory.createAnnuityPlanChart());
		addChart(FoundingChartFactory.createAmountChart());
		addChart(FoundingChartFactory.createInterestChart());

		setCenter(chartPane);
	}

	/**
	 * Add a {@link Chart}.
	 *
	 * @param chart
	 *            the chart
	 */
	private void addChart(final Chart<Founding> chart) {
		getComparison().getElements().forEach(f -> chart.add(f));
		chartPane.getChildren().add(chart.getChart());
	}

	/** the chart pane. */
	private TilePane chartPane;

}
