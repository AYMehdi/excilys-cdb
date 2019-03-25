package com.excilys.command;

import com.excilys.model.Page;
import com.excilys.model.Pagination;
import com.excilys.ui.CommandLineInterface;

public class Show {
	
	// ******** PRINCIAPAL METHOD *******
	public static void showPage(Page<?> page) {
		CommandLineInterface cli = new CommandLineInterface();
		
		Pagination pageAction = null;

		while (pageAction != Pagination.QUIT) {

			for (Object object : page.getPageData()) {
				System.out.println(object.toString());
			}

			System.out.print("Press 'N' to see the next page, 'P' for previous page or "
					+ "'Q' to quit the Computer DataBase Manager :\n");

			try {
				pageAction = Pagination.stringToAction(cli.getUserKeyboard().nextLine().trim());
				System.out.println();

				switch (pageAction) {
				case NEXT:
					page.next();
					break;
				case PREVIOUS:
					page.previous();
					break;
				case QUIT:
					break;
				case INVALID:
					System.out.println(
							"Sorry Mr, this is not a pagination action.\n"
							+ "Please enter 'N' for next Page, 'p' for Previous Page and 'Q' to Quit.\n");
					break;
				}
			} catch (NumberFormatException e1) {
				System.out.println(
						"Sorry Mr, this is not a pagination action.\n"
						+ "Please enter 'N' for next Page, 'p' for Previous Page and 's' to Stop.\n");
			}
			catch (NullPointerException e2) {
				System.out.println("Sorry Mr, Try again.\n");
			}
		}

	}
}
