package git_manager.constants;

public class OptionBar {
	public static final String TOOL_ICON_PATH = "/data/toolbar/";
	public static final String NOVICE_TOOL_ICON_PATH = TOOL_ICON_PATH
			+ "novice/";

	public static final String GIT_INIT_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitinit.png";
	public static final String GIT_ADD_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitadd.png";
	public static final String GIT_DIFF_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitdiff.png";
	public static final String GIT_PUSH_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitpush.png";
	public static final String GIT_REVERT_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitrevert.png";
	public static final String GIT_RM_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitrm.png";
	public static final String GIT_STATUS_ICON = NOVICE_TOOL_ICON_PATH
			+ "gitstatus.png";
	public static final String GIT_SNAP_ICON = NOVICE_TOOL_ICON_PATH
			+ "snapshot.png";

	public static final String ACTION_INIT = "GIT_INIT";
	public static final String ACTION_ADD = "GIT_ADD";
	public static final String ACTION_DIFF = "GIT_DIFF";
	public static final String ACTION_PUSH = "GIT_PUSH";
	public static final String ACTION_REVERT = "GIT_REVERT";
	public static final String ACTION_RM = "GIT_RM";
	public static final String ACTION_STATUS = "GIT_STATUS";
	public static final String ACTION_SNAP = "GIT_SNAP";

	public static final String MODE_MENU_ARROW = TOOL_ICON_PATH
			+ "mode-arrow.png"; // Mode
	// arrow
	// borrowed
	// from
	// processing

	public static final int ARROW_WIDTH = 7;
	public static final int ARROW_HEIGHT = 6;
	
	public static final int MODE_GAP_WIDTH = 8;
	public static final int MODE_BOX_HEIGHT = 6;
}
