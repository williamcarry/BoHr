package org.bohr.gui.laf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalButtonUI;

import org.bohr.gui.uiUtils.ColorUtils;

public class RoundRectButtonUI extends MetalButtonUI {
	private Color bgColor;

	public RoundRectButtonUI(Color color) {
		bgColor = color;
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setFont(c.getFont().deriveFont(Font.BOLD, 16F));
		c.setForeground(new Color(0xffffff));

		if (bgColor != null) {
			c.setBackground(bgColor);
		}

		if (c instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) c;
			button.setFocusPainted(false);
			button.setFocusable(false);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorder(null);
		}
	}

	/**
	 *
	 * @param g
	 * @param c
	 */
	protected void paintBackground_normal(Graphics g, JComponent c) {
		paintRoundRectButtonBackGround(g, c, c.getBackground());
	}

	/**
	 *
	 * @param g
	 * @param c
	 */
	protected void paintBackground_rollover(Graphics g, JComponent c) {
		Color color = ColorUtils.brightness(c.getBackground(), 1.2);
		paintRoundRectButtonBackGround(g, c, color);
	}

	/**
	 *
	 * @param g
	 * @param c
	 */
	protected void paintBackground_pressed(Graphics g, JComponent c) {
		Color color = ColorUtils.brightness(c.getBackground(), 0.8);
		paintRoundRectButtonBackGround(g, c, color);
	}

	protected void paintRoundRectButtonBackGround(Graphics g, JComponent c, Color color) {
		g.setColor(color);
		if (g != null && g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;

			int width = c.getWidth();
			int height = c.getHeight();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			RoundRectangle2D.Double rect = new RoundRectangle2D.Double();
			rect.setRoundRect(0, 0, width - 1, height - 1, 15, 15);
			g2.fill(rect);
		}
	}

	public void paintBackground(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		if (!model.isEnabled()) {
			paintBackground_normal(g, c);
		} else if (model.isPressed() && model.isArmed()) {
			paintBackground_pressed(g, c);
		} else if (b.isRolloverEnabled() && model.isRollover()) {
			paintBackground_rollover(g, c);
		} else {
			paintBackground_normal(g, c);
		}
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		paintBackground(g, c);
		super.paint(g, c);
	}
}
