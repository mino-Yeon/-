# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an educational algorithm visualization website that provides interactive, step-by-step simulations of permutation and combination algorithms. The project visualizes the transition from iterative to recursive implementations in Korean.

**Tech Stack**: Pure HTML/CSS/JavaScript (no build tools, no dependencies)

## Repository Structure

```
algo/
├── index.html              # Main landing page with navigation cards
├── 1.dupPerm/             # Duplicate Permutation (중복순열)
│   ├── *.java             # Original Java implementations
│   └── *.html             # Interactive HTML simulations
├── 2.permutation/         # Permutation (순열)
│   ├── *.java             # Original Java implementations
│   └── *.html             # Interactive HTML simulations
└── 3.combination/         # Combination (조합)
    ├── *.java             # Original Java implementations
    └── *.html             # Interactive HTML simulations
```

## Architecture

### HTML Simulation Files

Each HTML file is **self-contained** with inlined CSS and JavaScript. Common structure:

1. **Code Panel** (left): Syntax-highlighted Java code with active line highlighting
2. **Visualization Panel** (right):
   - Variable state display
   - Array/result visualization with animations
   - Boolean array state (for permutation)
   - Execution description
   - Recursion tree (for recursive versions) via SVG
   - Console output

### Key JavaScript Functions

Every simulation file follows this pattern:

```javascript
// 1. Code rendering
initCode()           // Renders Java source with syntax highlighting
function hl(text)    // Syntax highlighter

// 2. Step generation
genSteps()          // Generates array of execution steps
                    // Returns: [{l:lineNum, v:vars, r:result, o:output, ...}, ...]

// 3. Tree building (recursive versions only)
buildTree()         // Builds complete tree structure
initTree(treeData)  // Renders initial SVG tree

// 4. Rendering
render()            // Updates UI for current step (ci)
                    // - Highlights active code line
                    // - Updates variable displays
                    // - Animates array changes
                    // - Updates tree node states

// 5. Playback controls
next(), prev(), togglePlay(), skipOut(), doReset()
```

### Recursion Tree Visualization

**Critical Pattern Differences**:

- **Duplicate Permutation**: Full tree, all N children per node
- **Permutation**: Full dupPerm tree drawn, but `isSelected[i]` dynamically blocks paths (red dashed lines)
- **Combination**: Full dupPerm tree drawn, but `start` parameter structurally blocks paths (gray dashed lines)

**Node Label Convention**:
- Unvisited nodes: Show only `cnt` value
- Visited nodes: Show `(cnt, start)` or `(cnt)` depending on parameters
- Out-of-range nodes: Remain with `cnt` only (dimmed)

### Step Object Structure

```javascript
{
  l: lineNumber,           // Code line to highlight
  v: {cnt, i, ...},       // Current variable values
  r: [...],               // Result array state
  o: [...],               // Output array
  sk: [...],              // Call stack
  nid: nodeId,            // Tree node ID (recursive only)
  ns: {nodeId: state},    // Node states map
  nl: {nodeId: label},    // Node labels map (combination only)
  a: 'action',            // Action type: 'call', 'loop', 'set', 'print', 'skip', etc.
  d: 'description HTML'   // Human-readable step description
}
```

## Creating New Simulations

When adding a new algorithm simulation:

1. **Start with Java source**: Place in appropriate folder
2. **Create HTML file**: Copy structure from existing simulation in same category
3. **Update `genSteps()`**: Simulate algorithm execution, building steps array
4. **Update `render()`**: Map step data to visual updates
5. **For recursive algorithms**:
   - Implement `buildTree()` following the tree pattern (dupPerm/perm/comb)
   - Track node states in `ns` object throughout simulation
   - For combination-like algorithms: track `nodeLabels` for dynamic labeling
6. **Update `index.html`**: Add navigation card to appropriate section

### State Visualization Classes

CSS classes for visual states:
- Arrays: `.rb.written`, `.rb.active-slot`, `.rb.printed`, `.rb.stale`
- Tree nodes: `.tn-active`, `.tn-completed`, `.tn-back`, `.tn-skipped`, `.tn-outofrange`
- Tree edges: `.te-active`, `.te-completed`, `.te-back`, `.te-skipped`, `.te-outofrange`

## Testing

Open HTML files directly in browser. No build step required.

**Test checklist** for new simulations:
- [ ] Playback controls work (play, pause, step, reset)
- [ ] Keyboard shortcuts work (←→ Space R S)
- [ ] Speed control affects playback
- [ ] "Skip to next output" works
- [ ] Code highlighting matches execution
- [ ] Variable states update correctly
- [ ] Array animations are smooth
- [ ] Tree visualization (if recursive) shows correct state transitions
- [ ] R selector (if present) regenerates correct simulation
- [ ] Console output matches expected algorithm results

## Key Design Principles

1. **Self-contained files**: Each HTML should work standalone without external dependencies
2. **Consistent structure**: Follow the dual-panel layout (code left, visualization right)
3. **Step-by-step clarity**: Each step should clearly show what changed and why
4. **Progressive complexity**: Show evolution from iterative → recursive implementations
5. **Tree visualization accuracy**:
   - Always build full dupPerm tree first
   - Apply blocking/skipping visually during simulation
   - Update node labels dynamically based on visit status
6. **Korean language**: All UI text and descriptions in Korean
